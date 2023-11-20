package com.datacenter.datacenter.repository;



import com.datacenter.datacenter.model.Kelas;
import com.datacenter.datacenter.model.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface KelasRepository extends JpaRepository<Kelas, Long> {

    List<Kelas> findKelasBySekolah(Sekolah sekolah);
    @Modifying
    @Transactional
    @Query("delete from Kelas where id in(:longs)")
    void deleteByIdIn(List<Long> longs);
}
