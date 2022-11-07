package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SiswaRepository extends JpaRepository<Siswa, Long> {
    List<Siswa> findSiswaBySekolah(Sekolah sekolah);
    @Modifying
    @Transactional
    @Query("delete from Siswa where id in(:longs)")
    void deleteByIdIn(List<Long> longs);
}