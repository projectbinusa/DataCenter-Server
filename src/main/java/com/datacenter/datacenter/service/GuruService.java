package com.datacenter.datacenter.service;


import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.repository.GuruRepository;
import com.datacenter.datacenter.repository.SekolahRepository;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
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
import java.time.Year;
import java.util.List;

@Service
public class GuruService {
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/datacenter-a00ad.appspot.com/o/%s?alt=media";

    @Autowired
    GuruRepository guruRepository;

    @Autowired
    SekolahRepository sekolahRepository;

    public String uploadFilee(Long fileId, String fileName) throws IOException {
        // Create a temporary file with the specified filename
        File file = File.createTempFile("guru-image-", fileName);

        // Write the file contents to the temporary file
        // **Replace "your_file_contents" with the actual file contents**
        byte[] fileContents = "your_file_contents".getBytes();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContents);
        }

        // Upload the temporary file to Firebase Storage
        String downloadURL = uploadFileToFirebaseStoragee(file, fileName);

        // Delete the temporary file
        file.delete();

        return downloadURL;
    }
    private String uploadFileToFirebaseStoragee(File file, String fileName) throws IOException {
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
    public String getExtentionss(String fileName) {
        return fileName.split("\\.")[0];
    }

    public String imageConverterr(MultipartFile multipartFile) {
        try {
            String fileName = getExtentionss(multipartFile.getOriginalFilename());
            File file = convertToFilee(multipartFile, fileName);
            var RESPONSE_URL = uploadFilee(file, fileName);
            file.delete();
            return RESPONSE_URL;
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException("error  ");
        }
    }

    public File convertToFilee(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return file;
    }

    public List<Guru> getAllGuru() {
        return guruRepository.findAll();
    }

    public List<Guru> getGuruBySekolah(Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        return guruRepository.findGuruBySekolah(sekolah);
    }

    public Guru createGuru(Guru guru, Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        guru.setSekolah(sekolah);
        return guruRepository.save(guru);
    }

    public Guru editGuru(Long id, String namaGuru, String tanggalLahir, String tempatLahir, String setAgama, String gender, String umur, String noTelepon, String gelarPendidikan, String statusKawin ) {
        Guru guru = guruRepository.findById(id).orElse(null);
        guru.setNamaGuru(namaGuru);
        guru.setTanggalLahir(tanggalLahir);
        guru.setTempatLahir(tempatLahir);
        guru.setAgama(setAgama);
        guru.setGender(gender);
        guru.setUmur(umur);
        guru.setNoTelepon(noTelepon);
        guru.setGelarPendidikan(gelarPendidikan);
        guru.setStatusKawin(statusKawin);


        return guruRepository.save(guru);
    }
    public Guru getById(Long id) {
        return guruRepository.findById(id).orElse(null);
    }

    public void deleteGuru(Long id) {
        guruRepository.deleteById(id);
    }

    public void deleteAllBYIds(List<Long> longs) {
        guruRepository.deleteByIdIn(longs);
    }

    public Guru updateGuru(Guru existingGuru) {
        return guruRepository.save(existingGuru);
    }

    public String uploadFilee(File file, String fileName) throws IOException {
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
