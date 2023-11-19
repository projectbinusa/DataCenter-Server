package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.Guru;
import com.datacenter.datacenter.model.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface GuruRepository extends JpaRepository<Guru, Long> {
    List<Guru> findGuruBySekolah(Sekolah sekolah);
    @Modifying
    @Transactional
    @Query("delete from Guru where id in(:longs)")
    void deleteByIdIn(List<Long> longs);

}
