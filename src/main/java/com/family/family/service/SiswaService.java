package com.family.family.service;

import com.family.family.model.*;
import com.family.family.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SiswaService {
    @Autowired
    SiswaRepository siswaRepository;


    @Autowired
    UserRepository userRepository;

    public List<Siswa> getAllSiswa() {
        return siswaRepository.findAll();
    }

    public List<Siswa> getSiswaByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return siswaRepository.findSiswaByUser(user);
    }

    public List<Siswa> userSiswa(long id) {
        User user = userRepository.findById(id).orElse(null);
        return siswaRepository.findSiswaByUser(user);
    }

    public Siswa addSiswa(Siswa siswa, long id) {
        User user = userRepository.findById(id).orElse(null);
        siswa.setUser(user);
        return siswaRepository.save(siswa);
    }


    public Siswa updateSiswa(Long id, String namaSiswa, String tanggalLahir, String tempatLahir, String setAgama, String gender) {
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

    public void deleteFamily(Long id) {
        siswaRepository.deleteById(id);
    }
}
