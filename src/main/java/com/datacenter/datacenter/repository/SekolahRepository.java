package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SekolahRepository extends JpaRepository<Sekolah, Long> {
    Sekolah findSekolahByUser(User user);
}
