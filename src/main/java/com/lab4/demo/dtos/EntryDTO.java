package com.lab4.demo.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EntryDTO {

    private Long id;

    private String description;

    private String department;

    private Long userId;
}
