package com.medical.demo.repository;

import com.medical.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status,Long> {
    List<Status> findAllByUserIdEquals(Long id);
}
