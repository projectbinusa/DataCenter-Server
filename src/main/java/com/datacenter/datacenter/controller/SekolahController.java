package com.datacenter.datacenter.controller;

import com.datacenter.datacenter.exception.ResourceNotFoundException;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.repository.SekolahRepository;
import com.datacenter.datacenter.service.SekolahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SekolahController {
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/datacenter-a00ad.appspot.com/o/%s?alt=media";

    @Autowired
    SekolahService sekolahService;
    @Autowired
    SekolahRepository sekolahRepository;

    @GetMapping("/sekolah")
    public ResponseEntity<?> getAllSekolah() {
        List<Sekolah> sekolah = sekolahService.getAllSekolah();
        return new ResponseEntity<>(sekolah, HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/sekolah")
    public ResponseEntity<?> getSekolahByUserId(@PathVariable("userId") Long id) {
        Sekolah sekolah = sekolahService.getSekolahByUser(id);
        return new ResponseEntity<>(sekolah, HttpStatus.OK);
    }


    @PostMapping("/user/{userId}/add-sekolah")
    public ResponseEntity<?> addSekolah(@RequestBody Sekolah sekolah, @PathVariable("userId") Long id) {
        Sekolah sekolahh = sekolahService.createSekolah(sekolah, id);
        return new ResponseEntity<>(sekolahh, HttpStatus.CREATED);
    }
    @PutMapping("/sekolah/{sekolahId}/upload-image")
    public ResponseEntity<?> uploadImage(@PathVariable("sekolahId") Long id, @RequestBody MultipartFile image) throws IOException {
        if (id == null || id <= 0) {
            return new ResponseEntity<>("ID sekolah tidak valid", HttpStatus.BAD_REQUEST);
        }
        File file = sekolahService.convertToFile(image, getExtentions(image.getOriginalFilename()));
        String downloadURL = sekolahService.uploadFile(file, getExtentions(image.getOriginalFilename()));
        file.delete();
        Sekolah sekolah = sekolahService.getSekolahById(id);
        sekolah.setImage(downloadURL);
        sekolahService.updateSekolah(sekolah);
        return ResponseEntity.ok(sekolah);
    }

    public String getExtentions(String fileName) {
        return fileName.split("\\.")[0];
    }



    @GetMapping("/sekolah/{sekolahId}/image")
    public ResponseEntity<String> getImage(@PathVariable Long sekolahId) {
        Sekolah sekolah = sekolahRepository.findById(sekolahId).orElseThrow(() -> new ResourceNotFoundException("Sekolah tidak ditemukan"));

        return ResponseEntity.ok(sekolah.getImage());
    }


        @PutMapping("/sekolah/{sekolahId}")
        public ResponseEntity<?> editSekolah(@PathVariable("sekolahId") Long id, @RequestBody Sekolah updatedSekolah) {
        // Retrieve the existing Sekolah from the database using the provided id
        Sekolah existingSekolah = sekolahService.getSekolahById(id);

        if (existingSekolah != null) {
            // Update the properties of the existing Sekolah with the values from the updated Sekolah
            existingSekolah.setNamaSekolah(updatedSekolah.getNamaSekolah());
            existingSekolah.setAlamatSekolah(updatedSekolah.getAlamatSekolah());
            existingSekolah.setTeleponSekolah(updatedSekolah.getTeleponSekolah());
            existingSekolah.setAkreditasiSekolah(updatedSekolah.getAkreditasiSekolah());
            existingSekolah.setEmailSekolah(updatedSekolah.getEmailSekolah());
            existingSekolah.setStatus(updatedSekolah.getStatus());
            existingSekolah.setRuangKelas(updatedSekolah.getRuangKelas());
            existingSekolah.setInformasiSekolah(updatedSekolah.getInformasiSekolah());
            existingSekolah.setVisi(updatedSekolah.getVisi());
            existingSekolah.setMisi(updatedSekolah.getMisi());

            // Save the updated Sekolah back to the database
            Sekolah updatedSekolahEntity = sekolahService.updateSekolah(existingSekolah);

            return new ResponseEntity<>(updatedSekolahEntity, HttpStatus.OK);
        } else {
            // Handle the case where the Sekolah with the given id is not found
            return new ResponseEntity<>("Sekolah not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sekolah/{sekolahId}")
    public ResponseEntity<?> getSekolahById(@PathVariable("sekolahId") Long id) {
        Sekolah sekolah = sekolahService.getSekolahById(id);
        return new ResponseEntity<>(sekolah, HttpStatus.OK);
    }

    @GetMapping("/publik-sekolah/{sekolahId}")
    public ResponseEntity<?> getSekolahPublikById(@PathVariable("sekolahId") Long id) {
        Sekolah sekolah = sekolahService.getSekolahById(id);
        return new ResponseEntity<>(sekolah, HttpStatus.OK);
    }

    @DeleteMapping("/sekolah/{sekolahId}")
    public ResponseEntity<?> deleteSekolah(@PathVariable("sekolahId") Long id) {
        sekolahService.deleteSekolahById(id);
        return  new ResponseEntity<>("success deleted!", HttpStatus.OK);
    }

}

