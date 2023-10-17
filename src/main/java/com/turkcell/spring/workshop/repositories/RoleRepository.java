package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Role;
import com.turkcell.spring.workshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findById(int id);

}
