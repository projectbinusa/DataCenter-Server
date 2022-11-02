package com.datacenter.datacenter.controller;

import java.io.*;
import java.net.URLConnection;

import com.datacenter.datacenter.helper.ExcelHelper;
import com.datacenter.datacenter.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  public ResponseEntity<Resource> getFile(@PathVariable("id") Long id) {
    String filename = "siswa.xlsx";
    InputStreamResource file = new InputStreamResource(fileService.load(id));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
        .body(file);
  }

  private static final String EXTERNAL_FILE_PATH = "contoh-format.xlsx";

  @GetMapping("/download")
  public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
    File file = new File(EXTERNAL_FILE_PATH );
    if (file.exists()) {

      //get the mimetype
      String mimeType = URLConnection.guessContentTypeFromName(file.getName());
      if (mimeType == null) {
        //unknown mimetype so set the mimetype to application/octet-stream
        mimeType = "application/octet-stream";
      }

      response.setContentType(mimeType);

      response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

      //Here we have mentioned it to show as attachment
      //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

      response.setContentLength((int) file.length());

      InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

      FileCopyUtils.copy(inputStream, response.getOutputStream());

    }
  }
}