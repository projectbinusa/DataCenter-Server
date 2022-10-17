package com.family.family.controller;

import java.util.List;

import com.family.family.helper.ExcelHelper;
import com.family.family.model.Siswa;
import com.family.family.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {

  @Autowired
  ExcelService fileService;

  @PostMapping("/upload/user/{id}")
  public ResponseEntity<?> uploadFile(@PathVariable("id") long id , @RequestParam("file") MultipartFile file) {
    String message = "";
    if (ExcelHelper.hasExcelFormat(file)) {
      try {
        fileService.save(file,id);
        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(message);
      } catch (Exception e) {
        System.out.println(e);
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
      }
    }
    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @GetMapping("/download/{id}")
  public ResponseEntity<Resource> getFile(@PathVariable("id") long id) {
    String filename = "siswa.xlsx";
    InputStreamResource file = new InputStreamResource(fileService.load(id));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(file);
  }

}