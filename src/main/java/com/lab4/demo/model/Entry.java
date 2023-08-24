package com.lab4.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String title;
    @Column(length = 512, nullable = false)
    private String description;

    @Column(length = 512, nullable = false)
    private String department;

    @Column(length = 512, nullable = false)
    private String entryDate;
    @Column(length = 512, nullable = false)
    private Long userId;



}
