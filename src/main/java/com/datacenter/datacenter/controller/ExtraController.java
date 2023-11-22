package com.datacenter.datacenter.controller;


import com.datacenter.datacenter.model.Extra;
import com.datacenter.datacenter.model.Kelas;
 import com.datacenter.datacenter.service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ExtraController {
   @Autowired
   ExtraService extraService;


    @RequestMapping(value = "/extra", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Extra> extra = extraService.getAllExtraSekolah();
        return new ResponseEntity<>(extra, HttpStatus.OK);
    }

    @GetMapping("/extra/{sekolahId}/extra")
    public ResponseEntity<?> getExtraSekolahBySekolah(@PathVariable("sekolahId") Long id) {
        List<Extra> extra = extraService.getExtraSekolahBySekolah(id);
        return new ResponseEntity<>(extra, HttpStatus.OK);
    }

    @PostMapping("/extra/{sekolahId}/add-extra")
    public ResponseEntity<?> addExtra(@RequestBody Extra extra, @PathVariable("sekolahId") Long id) {
     Extra  extraa = extraService.createExtraSekolah(extra, id);
        return new ResponseEntity<>(extraa, HttpStatus.CREATED);
    }

    @PutMapping("/extra/extraId")
    public ResponseEntity<?> updateExtraSekolah(@PathVariable("extraId") Long id, @RequestBody Extra extra) {
        Extra extraa = extraService.updateExtraSekolah(id, extra.getNamaExtra(), extra.getStatus()  );
        return new ResponseEntity<>(extraa, HttpStatus.OK);
    }
    @GetMapping("/extra/{extraId}")
    public ResponseEntity<?> getById(@PathVariable("extraId") Long id) {
        Extra extraSekolah = extraService.getById(id);
        return new ResponseEntity<>(extraSekolah, HttpStatus.OK);
    }
    @DeleteMapping("/extra/{extraId}")
    public ResponseEntity<?> deleteFamily(@PathVariable("extraId") Long id) {
        extraService.deleteExtraSekolah(id);
        return new ResponseEntity<>("success delete!", HttpStatus.OK);
    }
    @DeleteMapping("/extra")
    public String delete(@RequestParam("ids") List<Long> ids) {
        extraService.deleteAllBYIds(ids);
        return String.join(",", ids.stream().map(value ->  Long.toString(value)).collect(Collectors.toList()));
    }

}
