package com.datacenter.datacenter.repository;

 import com.datacenter.datacenter.model.GelarPendidikan;
import com.datacenter.datacenter.model.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;

 import javax.transaction.Transactional;
import java.util.List;

public interface GelarPendidikanRepository extends JpaRepository<GelarPendidikan, Long> {

    List<GelarPendidikan>   findGelarPendidikanBySekolah(Sekolah sekolah);

    @Modifying
    @Transactional
    @Query("delete from GelarPendidikan where id in :ids")
    void deleteByIdIn(@Param("ids") List<Long> ids);
}