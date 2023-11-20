package com.datacenter.datacenter.controller;


import com.datacenter.datacenter.model.GelarPendidikan;
import com.datacenter.datacenter.service.GelarPendidikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class GelarPendidikanController {

    @Autowired
    GelarPendidikanService gelarPendidikanService;


    @RequestMapping(value = "/gelarPendidikan", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<GelarPendidikan> gelarPendidikan = gelarPendidikanService.getAllGelarPendidikan();
        return new ResponseEntity<>(gelarPendidikan, HttpStatus.OK);
    }

    @GetMapping("/gelarPendidikan/{sekolahId}/gelarPendidikan")
    public ResponseEntity<?> getGelarPendidikanBySekolahId(@PathVariable("sekolahId") Long id) {
        List<GelarPendidikan> gelarPendidikan = gelarPendidikanService.getGelarPendidikanBySekolah(id);
        return new ResponseEntity<>(gelarPendidikan, HttpStatus.OK);
    }

    @PostMapping("/gelarPendidikan/{sekolahId}/add-gelarPendidikan")
    public ResponseEntity<?> addGelarPendidikan(@RequestBody GelarPendidikan gelarPendidikan , @PathVariable("sekolahId") Long id) {
        GelarPendidikan gelarPendidikann = gelarPendidikanService.createGelarPendidikan(gelarPendidikan, id);
        return new ResponseEntity<>(gelarPendidikann, HttpStatus.CREATED);
    }

    @PutMapping("/gelarPendidikan/{gelarPendidikanId}") // Corrected path variable name
    public ResponseEntity<?> updateKelas(@PathVariable("gelarPendidikanId") Long id, @RequestBody GelarPendidikan gelarPendidikan) {
        GelarPendidikan gelarPendidikannn = gelarPendidikanService.updateGelarPendidikan(id, gelarPendidikan.getNamaGelar(), gelarPendidikan.getStatus());
        return new ResponseEntity<>(gelarPendidikannn, HttpStatus.OK);
    }

    @GetMapping("/gelarPendidikan/{gelarPendidikanId}")
    public ResponseEntity<?> getById(@PathVariable("gelarPendidikanId") Long id) {
        GelarPendidikan gelarPendidikan = gelarPendidikanService.getById(id);
        return new ResponseEntity<>(gelarPendidikan, HttpStatus.OK);
    }
    @DeleteMapping("/gelarPendidikan/{gelarPendidikanId}")
    public ResponseEntity<?> deleteFamily(@PathVariable("gelarPendidikanId") Long id) {
        gelarPendidikanService.deleteGelarPendidikan(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
    @DeleteMapping("/gelarPendidikan")
    public String delete(@RequestParam("ids") List<Long> ids) {
        gelarPendidikanService.deleteAllByIds(ids);
        return String.join(",", ids.stream().map(value ->  Long.toString(value)).collect(Collectors.toList()));
    }


}
