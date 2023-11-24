package com.datacenter.datacenter.service;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.User;
import com.datacenter.datacenter.repository.SekolahRepository;
import com.datacenter.datacenter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SekolahService {

    @Autowired
    SekolahRepository sekolahRepository;

    @Autowired
    UserRepository userRepository;

    public List<Sekolah> getAllSekolah() {
        return sekolahRepository.findAll();
    }

    public Sekolah getSekolahByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return sekolahRepository.findSekolahByUser(user);
    }

    public Sekolah createSekolah(Sekolah sekolah, Long id) {
        User user = userRepository.findById(id).orElse(null);
        sekolah.setUser(user);
        return sekolahRepository.save(sekolah);
    }

    public Sekolah updateSekolah(Long id, String namaSekolah, String alamatSekolah, String teleponSekolah,String akreditasiSekolah,String emailSekolah , String status, Integer ruangKelas ,String informasiSekolah ,String image) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        sekolah.setNamaSekolah(namaSekolah);
        sekolah.setAlamatSekolah(alamatSekolah);
        sekolah.setTeleponSekolah(teleponSekolah);
        sekolah.setAkreditasiSekolah(akreditasiSekolah);
        sekolah.setEmailSekolah(emailSekolah);
        sekolah.setStatus(status);
        sekolah.setRuangKelas(ruangKelas);
        sekolah.setInformasiSekolah(informasiSekolah);
        sekolah.setImage(image);
        return sekolahRepository.save(sekolah);
    }

    public Sekolah getSekolahById(Long id) {
        return sekolahRepository.findById(id).orElse(null);
    }

    public void deleteSekolahById(Long id) {
        sekolahRepository.deleteById(id);
    }

    public Sekolah updateSekolah(Sekolah existingSekolah) {
        return sekolahRepository.save(existingSekolah);
    }
}
