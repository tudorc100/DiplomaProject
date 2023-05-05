package com.lab4.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false,columnDefinition = "TIMESTAMP (6)")
    private Timestamp timestamp;

    @Column(length = 512, nullable = false)
    private String status;

    @Column(length = 512, nullable = false)
    private Long userId;

    public Status(Timestamp timestamp, String status, Long user_id) {
        this.timestamp=timestamp;
        this.status=status;
        this.userId=user_id;
    }
}
