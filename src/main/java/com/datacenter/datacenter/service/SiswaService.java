package com.datacenter.datacenter.service;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import com.datacenter.datacenter.repository.SekolahRepository;
import com.datacenter.datacenter.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
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
        siswa.setTahunDaftar(Year.now().getValue());
        siswa.setSekolah(sekolah);
        return siswaRepository.save(siswa);
    }

    public Siswa editSiswa(Long id, String namaMurid, String extrakulikuler, String tempatLahir, String tanggalLahir, String umur, String agama, String gender,String kelas, String namaOrtu, String noTeleponOrtu ) {
        Siswa siswa = siswaRepository.findById(id).orElse(null);
        siswa.setNamaMurid(namaMurid);
        siswa.setExtrakulikuler(extrakulikuler);
        siswa.setTempatLahir(tempatLahir);
        siswa.setTanggalLahir(tanggalLahir);
        siswa.setUmur(umur);
        siswa.setAgama(agama);
        siswa.setGender(gender);
        siswa.setKelas(kelas);
        siswa.setNamaOrtu(namaOrtu);
        siswa.setNoTeleponOrtu(noTeleponOrtu);
        return siswaRepository.save(siswa);
    }

    public Siswa getById(Long id) {
        return siswaRepository.findById(id).orElse(null);
    }

    public void deleteSiswa(Long id) {
        siswaRepository.deleteById(id);
    }

    public void deleteAllBYIds(List<Long> longs) {
        siswaRepository.deleteByIdIn(longs);
    }
}
