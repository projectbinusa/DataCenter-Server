package com.datacenter.datacenter.service;



import com.datacenter.datacenter.model.Kelas;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.repository.KelasRepository;
import com.datacenter.datacenter.repository.SekolahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KelasService {

    @Autowired
    KelasRepository kelasRepository;
    @Autowired
    SekolahRepository sekolahRepository;

    public List<Kelas> getAllKelas() {
        return kelasRepository.findAll();
    }

    public List<Kelas> getKelasBySekolah(Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        return kelasRepository.findKelasBySekolah(sekolah);
    }

    public Kelas createKelas(Kelas kelas, Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        kelas.setSekolah(sekolah);
        return kelasRepository.save(kelas);
    }

    public Kelas  updateKelas(Long id, String namaKelas, String status  ) {
        Kelas kelas = kelasRepository.findById(id).orElse(null);
        kelas.setNamaKelas(namaKelas);
        kelas.setStatus(status);


        return kelasRepository.save(kelas);
    }
    public Kelas getById(Long id) {
        return kelasRepository.findById(id).orElse(null);
    }

    public void deletekelas(Long id) {
        kelasRepository.deleteById(id);
    }

    public void deleteAllBYIds(List<Long> longs) {
        kelasRepository.deleteByIdIn(longs);
    }

}



