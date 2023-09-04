package com.medical.demo.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EntryDTO {

    private Long id;

    private String title;
    private String description;

    private String department;

    private String entryDate;

    private Long userId;
}
