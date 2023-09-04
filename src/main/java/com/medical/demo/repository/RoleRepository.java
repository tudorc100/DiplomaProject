package com.medical.demo.repository;

import com.medical.demo.model.ERole;
import com.medical.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
    Role findRoleByName(String name);
}
