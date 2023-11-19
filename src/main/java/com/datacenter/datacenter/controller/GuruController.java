package com.datacenter.datacenter.controller;

import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class GuruController {

    @Autowired
    GuruService guruService;

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
