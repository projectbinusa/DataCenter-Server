package com.datacenter.datacenter.controller;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import com.datacenter.datacenter.service.SekolahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SekolahController {

    @Autowired
    SekolahService sekolahService;

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

    @PutMapping(value = "/sekolah/{sekolahId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> editSekolah(@PathVariable("sekolahId") Long id, @RequestParam("file") MultipartFile file, @RequestPart("updatedSekolah") Sekolah updatedSekolah) {
        try {
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

                // Update the image only if a new file is provided
                if (file != null && !file.isEmpty()) {
                    existingSekolah.setImage(file.getBytes());
                }

                // Save the updated Sekolah back to the database
                Sekolah updatedSekolahEntity = sekolahService.updateSekolah(existingSekolah);

                return new ResponseEntity<>(updatedSekolahEntity, HttpStatus.OK);
            } else {
                // Handle the case where the Sekolah with the given id is not found
                return new ResponseEntity<>("Sekolah not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle exceptions, log them, and return an error response
            e.printStackTrace(); // Log the exception properly in your actual application
            return new ResponseEntity<>("Error processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
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

