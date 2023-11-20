package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.Extra;

import com.datacenter.datacenter.model.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ExtraRepository extends JpaRepository<Extra, Long> {
    List<Extra> findExtraSekolahBySekolah(Sekolah sekolah);

    @Modifying
    @Transactional
    @Query("delete from Extra Sekolah where id in(:longs)")
    void deleteByIdIn(List<Long> longs);
}
