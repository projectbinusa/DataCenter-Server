package com.datacenter.datacenter.service;

import com.datacenter.datacenter.helper.ExcelHelper;
import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import com.datacenter.datacenter.repository.GuruRepository;
import com.datacenter.datacenter.repository.SekolahRepository;
import com.datacenter.datacenter.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Service
public class ExcelServiceGuru {
    @Autowired
    GuruRepository repository;
    @Autowired
    SekolahRepository sekolahRepository;
    @Autowired
    GuruRepository guruRepository;
    private Sekolah sekolah;

    private Guru guru;

    public void savee(MultipartFile file, long id) {
        try {
            Guru guru = guruRepository.findById(id).orElse(null);
            List<Guru> gurus = ExcelHelper.excelToGurus(file.getInputStream(), sekolah);
            guruRepository.saveAll(gurus);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load(Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        List<Guru> gurus = repository.findGuruBySekolah(sekolah);
        ByteArrayInputStream in = ExcelHelper.gurusToExcel(gurus);
        return in;
    }
}
