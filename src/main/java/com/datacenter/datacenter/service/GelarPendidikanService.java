package com.datacenter.datacenter.service;

import com.datacenter.datacenter.model.GelarPendidikan;
import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.repository.GelarPendidikanRepository;
import com.datacenter.datacenter.repository.SekolahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GelarPendidikanService {

  @Autowired
    SekolahRepository sekolahRepository;

  @Autowired
    GelarPendidikanRepository gelarPendidikanRepository;

    public List<GelarPendidikan> getAllGelarPendidikan() {
        return gelarPendidikanRepository.findAll();
    }

    public List<GelarPendidikan> getGelarPendidikanBySekolah(Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        return gelarPendidikanRepository.findGelarPendidikanBySekolah(sekolah);
    }

    public GelarPendidikan createGelarPendidikan(GelarPendidikan gelarPendidikan, Long id) {
        Sekolah sekolah = sekolahRepository.findById(id).orElse(null);
        gelarPendidikan.setSekolah(sekolah);
        return gelarPendidikanRepository.save(gelarPendidikan);
    }

    public GelarPendidikan  updateGelarPendidikan(Long id, String namaGelar, String status  ) {
       GelarPendidikan gelarPendidikan = gelarPendidikanRepository.findById(id).orElse(null);
        gelarPendidikan.setNamaGelar(namaGelar);
        gelarPendidikan.setStatus(status);


        return gelarPendidikanRepository.save(gelarPendidikan);
    }
    public GelarPendidikan getById(Long id) {
        return gelarPendidikanRepository.findById(id).orElse(null);
    }

    public void deleteGelarPendidikan(Long id) {
        gelarPendidikanRepository.deleteById(id);
    }

    public void deleteAllByIds(List<Long> ids) {
        gelarPendidikanRepository.deleteByIdIn(ids);
    }


}
