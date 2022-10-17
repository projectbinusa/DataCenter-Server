package com.family.family.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.family.family.helper.ExcelHelper;
import com.family.family.model.Siswa;
import com.family.family.model.User;
import com.family.family.repository.SiswaRepository;
import com.family.family.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {
  @Autowired
  SiswaRepository repository;
  @Autowired
  UserRepository userRepository;

  public void save(MultipartFile file,long id) {
    try {
      User user = userRepository.findById(id).orElse(null);
      List<Siswa> siswa = ExcelHelper.excelToSiswas(file.getInputStream(),user);
      repository.saveAll(siswa);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load(long id) {
    User user = userRepository.findById(id).orElse(null);
    List<Siswa> siswa = repository.findSiswaByUser(user);
    ByteArrayInputStream in = ExcelHelper.siswasToExcel(siswa);
    return in;
  }

}