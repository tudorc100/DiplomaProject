package com.lab4.demo.repository;

import com.lab4.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
