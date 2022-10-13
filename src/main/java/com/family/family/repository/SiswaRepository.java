package com.family.family.repository;

import com.family.family.model.Siswa;
import com.family.family.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiswaRepository extends JpaRepository<Siswa, Long> {
    List<Siswa> findSiswaByUser(User user);
}
