package com.example.app.repo;

import com.example.app.entities.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticatedUser extends JpaRepository<AuthorizedUser,Long> {
    Optional<AuthorizedUser> findByUsername(String username);
}
