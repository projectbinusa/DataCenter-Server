package com.datacenter.datacenter.service;


import com.datacenter.datacenter.model.Extra;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.repository.ExtraRepository;
 import com.datacenter.datacenter.repository.SekolahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraService {


@Autowired
SekolahRepository sekolahRepository;

@Autowired
ExtraRepository extraSekolahRepository;
    public List<Extra> getAllExtraSekolah() {
        return extraSekolahRepository.findAll();
    }

    public List<Extra> getExtraSekolahBySekolah(Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        return extraSekolahRepository.findExtraSekolahBySekolah(sekolah);
    }

    public Extra createExtraSekolah(Extra extra, Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        extra.setSekolah(sekolah);
        return extraSekolahRepository.save(extra );
    }

    public Extra  updateExtraSekolah(Long id, String namaExtra, String status  ) {
     Extra extra  = extraSekolahRepository.findById(id).orElse(null);
        extra.setNamaExtra(namaExtra);
        extra.setStatus(status);


        return extraSekolahRepository.save(extra);
    }
    public Extra getById(Long id) {
        return extraSekolahRepository.findById(id).orElse(null);
    }

    public void deleteExtraSekolah(Long id) {
        extraSekolahRepository.deleteById(id);
    }

    public void deleteAllBYIds(List<Long> longs) {
        extraSekolahRepository.deleteByIdIn(longs);
    }

}
