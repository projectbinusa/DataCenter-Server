package com.datacenter.datacenter.controller;

import com.datacenter.datacenter.exception.ResourceNotFoundException;
import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.repository.GuruRepository;
import com.datacenter.datacenter.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class GuruController {
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/datacenter-a00ad.appspot.com/o/%s?alt=media";

    @Autowired
    GuruService guruService;

    @Autowired
    GuruRepository guruRepository;

    @RequestMapping(value = "/guru", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Guru> guru = guruService.getAllGuru();
        return new ResponseEntity<>(guru, HttpStatus.OK);
    }

    @GetMapping("/guru/{sekolahId}/guru")
    public ResponseEntity<?> getGuruBySekolahId(@PathVariable("sekolahId") Long id) {
        List<Guru> guru = guruService.getGuruBySekolah(id);
        return new ResponseEntity<>(guru, HttpStatus.OK);
    }

    @PostMapping("/guru/{sekolahId}/add-guru")
    public ResponseEntity<?> addGuru(@RequestBody Guru guru, @PathVariable("sekolahId") Long id) {
        Guru guruu = guruService.createGuru(guru, id);
        return new ResponseEntity<>(guruu, HttpStatus.CREATED);
    }

    @PutMapping("/guru/{guruId}/upload-image")
    public ResponseEntity<?> uploadImageForGuru(@PathVariable("guruId") Long id, @RequestBody MultipartFile image) throws IOException {
        if (id == null || id <= 0) {
            return new ResponseEntity<>("ID guru tidak valid", HttpStatus.BAD_REQUEST);
        }
        File file = guruService.convertToFilee(image, getExtentionss(image.getOriginalFilename()));
        String downloadURL = guruService.uploadFilee(file, getExtentionss(image.getOriginalFilename()));
        file.delete();
        Guru guru = guruService.getById(id);
        guru.setImage(downloadURL);
        guruService.updateGuru(guru);
        return ResponseEntity.ok(guru);
    }

    public String getExtentionss(String fileName) {
        return fileName.split("\\.")[0];
    }

    @GetMapping("/guru/{guruId}/image")
    public ResponseEntity<String> getImage(@PathVariable Long guruId) {
        Guru guru = guruRepository.findById(guruId).orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan"));

        return ResponseEntity.ok(guru.getImage());
    }

    @PutMapping("/guru/{guruId}")
    public ResponseEntity<?> updateGuru(@PathVariable("guruId") Long id, @RequestBody Guru guru) {
        Guru guruuu = guruService.editGuru(id, guru.getNamaGuru(), guru.getTanggalLahir(), guru.getTempatLahir(), guru.getAgama(), guru.getGender(), guru.getUmur(), guru.getNoTelepon(), guru.getGelarPendidikan(), guru.getStatusKawin());
        return new ResponseEntity<>(guruuu, HttpStatus.OK);
    }
    @GetMapping("/guru/{guruId}")
    public ResponseEntity<?> getById(@PathVariable("guruId") Long id) {
        Guru guruuu = guruService.getById(id);
        return new ResponseEntity<>(guruuu, HttpStatus.OK);
    }
    @DeleteMapping("/guru/{guruId}")
    public ResponseEntity<?> deleteFamily(@PathVariable("guruId") Long id) {
        guruService.deleteGuru(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
    @DeleteMapping("/guru")
    public String delete(@RequestParam("ids") List<Long> ids) {
        guruService.deleteAllBYIds(ids);
        return String.join(",", ids.stream().map(value ->  Long.toString(value)).collect(Collectors.toList()));
    }
}
