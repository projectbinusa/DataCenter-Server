package com.datacenter.datacenter.service;


import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.repository.GuruRepository;
import com.datacenter.datacenter.repository.SekolahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Year;
import java.util.List;

@Service
public class GuruService {
    @Autowired
    GuruRepository guruRepository;

    @Autowired
    SekolahRepository sekolahRepository;

    public List<Guru> getAllGuru() {
        return guruRepository.findAll();
    }

    public List<Guru> getGuruBySekolah(Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        return guruRepository.findGuruBySekolah(sekolah);
    }

    public Guru createGuru(Guru guru, Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        guru.setSekolah(sekolah);
        return guruRepository.save(guru);
    }

    public Guru editGuru(Long id, String namaGuru, String tanggalLahir, String tempatLahir, String setAgama, String gender, String umur, String noTelepon, String gelarPendidikan, String statusKawin ) {
        Guru guru = guruRepository.findById(id).orElse(null);
        guru.setNamaGuru(namaGuru);
        guru.setTanggalLahir(tanggalLahir);
        guru.setTempatLahir(tempatLahir);
        guru.setAgama(setAgama);
        guru.setGender(gender);
        guru.setUmur(umur);
        guru.setNoTelepon(noTelepon);
        guru.setGelarPendidikan(gelarPendidikan);
        guru.setStatusKawin(statusKawin);

        return guruRepository.save(guru);
    }
    public Guru getById(Long id) {
        return guruRepository.findById(id).orElse(null);
    }

    public void deleteGuru(Long id) {
        guruRepository.deleteById(id);
    }

    public void deleteAllBYIds(List<Long> longs) {
        guruRepository.deleteByIdIn(longs);
    }

}
