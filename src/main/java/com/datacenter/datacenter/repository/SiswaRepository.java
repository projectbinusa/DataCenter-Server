package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiswaRepository extends JpaRepository<Siswa, Long> {
    List<Siswa> findSiswaBySekolah(Sekolah sekolah);
}