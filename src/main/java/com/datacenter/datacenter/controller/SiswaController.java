
package com.datacenter.datacenter.controller;

import com.datacenter.datacenter.service.SiswaService;
import com.datacenter.datacenter.model.Siswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SiswaController {
    @Autowired
    SiswaService siswaService;

    @RequestMapping(value = "/siswa", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Siswa> siswa = siswaService.getAllSiswa();
        return new ResponseEntity<>(siswa, HttpStatus.OK);
    }

    @GetMapping("/sekolah/{sekolahId}/siswa")
    public ResponseEntity<?> getSiswaBySekolahId(@PathVariable("sekolahId") Long id) {
        List<Siswa> siswa = siswaService.getSiswaBySekolah(id);
        return new ResponseEntity<>(siswa, HttpStatus.OK);
    }

    @PostMapping("/sekolah/{sekolahId}/add-siswa")
    public ResponseEntity<?> addSiswa(@RequestBody Siswa siswa, @PathVariable("sekolahId") Long id) {
        Siswa siswaa = siswaService.createSiswa(siswa, id);
        return new ResponseEntity<>(siswaa, HttpStatus.CREATED);
    }

    @PutMapping("/siswa/{siswaId}")
    public ResponseEntity<?> updateSiswa(@PathVariable("siswaId") Long id, @RequestBody Siswa siswa) {
        Siswa siswaaa = siswaService.editSiswa(
                id,
                siswa.getNamaMurid(),
                siswa.getExtrakulikuler(),
                siswa.getTempatLahir(),
                siswa.getTanggalLahir(),
                siswa.getUmur(),
                siswa.getAgama(),
                siswa.getGender(),
                siswa.getKelas(),
                siswa.getNamaOrtu(),
                siswa.getNoTeleponOrtu()
                );
        return new ResponseEntity<>(siswaaa, HttpStatus.OK);
    }


    @GetMapping("/siswa/{siswaId}")
    public ResponseEntity<?> getById(@PathVariable("siswaId") Long id) {
        Siswa siswaaa = siswaService.getById(id);
        return new ResponseEntity<>(siswaaa, HttpStatus.OK);
    }

    @DeleteMapping("/siswa/{siswaId}")
    public ResponseEntity<?> deleteFamily(@PathVariable("siswaId") Long id) {
        siswaService.deleteSiswa(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }

    @DeleteMapping("/siswa")
    public ResponseEntity<String> delete(@RequestParam("ids") List<Long> ids) {
        siswaService.deleteAllBYIds(ids);
        String deletedIds = String.join(",", ids.stream().map(String::valueOf).collect(Collectors.toList()));
        return new ResponseEntity<>(deletedIds, HttpStatus.OK);
    }
}