package com.datacenter.datacenter.repository;

import com.datacenter.datacenter.model.User;
import com.google.firebase.database.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByRole(String role );

    Boolean existsByEmail(String email);
}
