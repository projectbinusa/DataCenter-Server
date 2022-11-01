package com.datacenter.datacenter.controller;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import com.datacenter.datacenter.service.SekolahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<Sekolah> sekolah = sekolahService.getSekolahByUser(id);
        return new ResponseEntity<>(sekolah, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/add-sekolah")
    public ResponseEntity<?> addSekolah(@RequestBody Sekolah sekolah, @PathVariable("userId") Long id) {
        Sekolah sekolahh = sekolahService.createSekolah(sekolah, id);
        return new ResponseEntity<>(sekolahh, HttpStatus.CREATED);
    }

    @PutMapping("/sekolah/{sekolahId}")
    public ResponseEntity<?> editSekolah(@PathVariable("sekolahId") Long id, @RequestBody Sekolah sekolah) {
        Sekolah sekolahh = sekolahService.updateSekolah(id, sekolah.getNamaSekolah(), sekolah.getAlamatSekolah(), sekolah.getTeleponSekolah());
        return new ResponseEntity<>(sekolahh, HttpStatus.OK);
    }

    @GetMapping("/sekolah/{sekolahId}")
    public ResponseEntity<?> getSekolahById(@PathVariable("sekolahId") Long id) {
        Sekolah sekolah = sekolahService.getSekolahById(id);
        return new ResponseEntity<>(sekolah, HttpStatus.OK);
    }

}
