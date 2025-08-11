package com.example.app.repo;

import com.example.app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRole extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
