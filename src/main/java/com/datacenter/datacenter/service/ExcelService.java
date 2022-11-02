package com.datacenter.datacenter.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.datacenter.datacenter.repository.SiswaRepository;
import com.datacenter.datacenter.helper.ExcelHelper;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import com.datacenter.datacenter.repository.SekolahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {
  @Autowired
  SiswaRepository repository;
  @Autowired
  SekolahRepository sekolahRepository;

  public void save(MultipartFile file,long id) {
    try {
      Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
      List<Siswa> siswa = ExcelHelper.excelToSiswas(file.getInputStream(),sekolah);
      repository.saveAll(siswa);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load(Long id) {
    Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
    List<Siswa> siswa = repository.findSiswaBySekolah(sekolah);
    ByteArrayInputStream in = ExcelHelper.siswasToExcel(siswa);
    return in;
  }

}