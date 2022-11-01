package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.Siswa;
import com.datacenter.datacenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SekolahRepository extends JpaRepository<Sekolah, Long> {
    List<Sekolah> findSekolahByUser(User user);
}
