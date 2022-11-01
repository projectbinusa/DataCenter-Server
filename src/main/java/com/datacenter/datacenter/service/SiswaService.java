package com.datacenter.datacenter.service;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import com.datacenter.datacenter.repository.SekolahRepository;
import com.datacenter.datacenter.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SiswaService {
    @Autowired
    SiswaRepository siswaRepository;

    @Autowired
    SekolahRepository sekolahRepository;

    public List<Siswa> getAllSiswa() {
        return siswaRepository.findAll();
    }

    public List<Siswa> getSiswaBySekolah(Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        return siswaRepository.findSiswaBySekolah(sekolah);
    }

    public Siswa createSiswa(Siswa siswa, Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        siswa.setSekolah(sekolah);
        return siswaRepository.save(siswa);
    }

    public Siswa editSiswa(Long id, String namaSiswa, String tanggalLahir, String tempatLahir, String setAgama, String gender) {
        Siswa siswa = siswaRepository.findById(id).orElse(null);
        siswa.setNamaSiswa(namaSiswa);
        siswa.setTanggalLahir(tanggalLahir);
        siswa.setTempatLahir(tempatLahir);
        siswa.setAgama(setAgama);
        siswa.setGender(gender);
        return siswaRepository.save(siswa);
    }

    public Siswa getById(Long id) {
        return siswaRepository.findById(id).orElse(null);
    }

    public void deleteSiswa(Long id) {
        siswaRepository.deleteById(id);
    }

}
