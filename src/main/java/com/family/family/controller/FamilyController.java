package com.family.family.controller;

import com.family.family.model.*;
import com.family.family.repository.SiswaRepository;
import com.family.family.repository.UserRepository;
import com.family.family.service.SiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class FamilyController {
    @Autowired
    SiswaService siswaService;

    @Autowired
    SiswaRepository siswaRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/siswa", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Siswa> siswa = siswaService.getAllSiswa();
        return new ResponseEntity<>(siswa, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/siswa", method = RequestMethod.GET)
    public ResponseEntity<?> getSiswaByUserId(@PathVariable("userId") Long id) {
        List<Siswa> siswa = siswaService.getSiswaByUser(id);
        return new ResponseEntity<>(siswa, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/siswa", method = RequestMethod.POST)
    public ResponseEntity<?> createSiswa(@RequestBody Siswa siswa, @PathVariable("userId") long id) {
        Siswa siswas = siswaService.addSiswa(siswa, id);
        return new ResponseEntity<>(siswas, HttpStatus.OK);
    }

    @RequestMapping(value = "/siswa/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFamily(@PathVariable("id") Long id, @RequestBody Siswa siswa) {
        Siswa siswaaa = siswaService.updateSiswa(id, siswa.getNamaSiswa(), siswa.getTanggalLahir(), siswa.getTempatLahir(), siswa.getAgama(), siswa.getGender());
        return new ResponseEntity<>(siswaaa, HttpStatus.OK);
    }

    @RequestMapping(value = "/siswa/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Siswa siswaaa = siswaService.getById(id);
        return new ResponseEntity<>(siswaaa, HttpStatus.OK);
    }

    @RequestMapping(value = "/siswa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFamily(@PathVariable("id") Long id) {
        siswaService.deleteSiswa(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
}
