package com.medical.demo.repository;

import com.medical.demo.model.Role;
import com.medical.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findAllByRolesContaining(Optional<Role> roles);

    User findUserByCnp(String cnp);

    User findUserByUsernameEquals(String username);


}
