package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.Sekolah;
import com.datacenter.datacenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Repository
public interface SekolahRepository extends JpaRepository<Sekolah, Long> {
    Sekolah findSekolahByUser(User user);
   @Modifying
    @Transactional
    void deleteByUserId(Long userId);
}

