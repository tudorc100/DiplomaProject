package com.medical.demo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class StatusDTO {

    private Long id;
    private String timestamp;
    private String status;
    private Long userId;

    public StatusDTO() {
    }

    public StatusDTO(Long id, String timestamp, String status, Long userId) {
        this.id = id;
        this.timestamp = timestamp;
        this.status = status;
        this.userId = userId;
    }

}

