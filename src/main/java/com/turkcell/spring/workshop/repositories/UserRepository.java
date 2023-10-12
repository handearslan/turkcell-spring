package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
