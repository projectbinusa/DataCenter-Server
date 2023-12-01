package com.datacenter.datacenter.service;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.User;
import com.datacenter.datacenter.repository.SekolahRepository;
import com.datacenter.datacenter.repository.UserRepository;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Service
public class SekolahService {
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/datacenter-a00ad.appspot.com/o/%s?alt=media";



    @Autowired
    SekolahRepository sekolahRepository;

    @Autowired
    UserRepository userRepository;

    public String uploadFile(Long fileId, String fileName) throws IOException {
        // Create a temporary file with the specified filename
        File file = File.createTempFile("sekolah-image-", fileName);

        // Write the file contents to the temporary file
        // **Replace "your_file_contents" with the actual file contents**
        byte[] fileContents = "your_file_contents".getBytes();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContents);
        }

        // Upload the temporary file to Firebase Storage
        String downloadURL = uploadFileToFirebaseStorage(file, fileName);

        // Delete the temporary file    
        file.delete();

        return downloadURL;
    }
    private String uploadFileToFirebaseStorage(File file, String fileName) throws IOException {
        // Create a BlobId object with the image data and metadata
        BlobId blobId = BlobId.of("datacenter-a00ad.appspot.com", file.getName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();

        // Get the credentials for accessing Firebase Storage
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/ServiceAccount.json"));

        // Get the Storage service
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // Upload the image data to Firebase Storage
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        // Generate the download URL for the image
        return String.format(DOWNLOAD_URL, URLEncoder.encode(file.getName(), StandardCharsets.UTF_8));
    }
    public String getExtentions(String fileName) {
        return fileName.split("\\.")[0];
    }

    public String imageConverter(MultipartFile multipartFile) {
        try {
            String fileName = getExtentions(multipartFile.getOriginalFilename());
            File file = convertToFile(multipartFile, fileName);
            var RESPONSE_URL = uploadFile(file, fileName);
            file.delete();
            return RESPONSE_URL;
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException("error  ");
        }
    }

    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return file;
    }

    public List<Sekolah> getAllSekolah() {
        return sekolahRepository.findAll();
    }

    public Sekolah getSekolahByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return sekolahRepository.findSekolahByUser(user);
    }

    public Sekolah createSekolah(Sekolah sekolah, Long id) {
        User user = userRepository.findById(id).orElse(null);
        sekolah.setUser(user);
        return sekolahRepository.save(sekolah);
    }


    public Sekolah editSekolah(Long id, String namaSekolah, String alamatSekolah, String teleponSekolah,String akreditasiSekolah,String emailSekolah , String status, Integer ruangKelas ,String informasiSekolah ,String visiMisi) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        sekolah.setNamaSekolah(namaSekolah);
        sekolah.setAlamatSekolah(alamatSekolah);
        sekolah.setTeleponSekolah(teleponSekolah);
        sekolah.setAkreditasiSekolah(akreditasiSekolah);
        sekolah.setEmailSekolah(emailSekolah);
        sekolah.setStatus(status);
        sekolah.setRuangKelas(ruangKelas);
        sekolah.setInformasiSekolah(informasiSekolah);
        sekolah.setVisiMisi(visiMisi);
        return sekolahRepository.save(sekolah);
    }

    public Sekolah getSekolahById(Long id) {
        return sekolahRepository.findById(id).orElse(null);
    }

    public void deleteSekolahById(Long id) {
        sekolahRepository.deleteById(id);
    }

    public Sekolah updateSekolah(Sekolah existingSekolah) {
        return sekolahRepository.save(existingSekolah);
    }


    public String uploadFile(File file, String fileName) throws IOException {
        // Create a BlobId object with the image data and metadata
        BlobId blobId = BlobId.of("datacenter-a00ad.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();

        // Get the credentials for accessing Firebase Storage
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/ServiceAccount.json"));

        // Get the Storage service
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // Upload the image data to Firebase Storage
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        // Generate the download URL for the image
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }



}
