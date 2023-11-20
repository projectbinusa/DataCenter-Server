package com.datacenter.datacenter.controller;


import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.model.Kelas;
import com.datacenter.datacenter.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class KelasController {

    @Autowired
    KelasService kelasService;

    @RequestMapping(value = "/kelas", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Kelas> kelas = kelasService.getAllKelas();
        return new ResponseEntity<>(kelas, HttpStatus.OK);
    }

    @GetMapping("/kelas/{sekolahId}/kelas")
    public ResponseEntity<?> getKelasBySekolahId(@PathVariable("sekolahId") Long id) {
        List<Kelas> kelas = kelasService.getKelasBySekolah(id);
        return new ResponseEntity<>(kelas, HttpStatus.OK);
    }

    @PostMapping("/kelas/{sekolahId}/add-kelas")
    public ResponseEntity<?> addKelas(@RequestBody Kelas kelas, @PathVariable("sekolahId") Long id) {
        Kelas kelass = kelasService.createKelas(kelas, id);
        return new ResponseEntity<>(kelass, HttpStatus.CREATED);
    }

    @PutMapping("/kelas/{kelasId}")
    public ResponseEntity<?> updateKelas(@PathVariable("kelasId") Long id, @RequestBody Kelas kelas) {
        Kelas kelasss = kelasService.updateKelas(id, kelas.getNamaKelas(), kelas.getStatus()  );
        return new ResponseEntity<>(kelasss, HttpStatus.OK);
    }
    @GetMapping("/kelas/{kelasId}")
    public ResponseEntity<?> getById(@PathVariable("kelasId") Long id) {
        Kelas kelas = kelasService.getById(id);
        return new ResponseEntity<>(kelas, HttpStatus.OK);
    }
    @DeleteMapping("/kelas/{kelasId}")
    public ResponseEntity<?> deleteFamily(@PathVariable("kelasId") Long id) {
        kelasService.deletekelas(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
    @DeleteMapping("/kelas")
    public String delete(@RequestParam("ids") List<Long> ids) {
        kelasService.deleteAllBYIds(ids);
        return String.join(",", ids.stream().map(value ->  Long.toString(value)).collect(Collectors.toList()));
    }


}
