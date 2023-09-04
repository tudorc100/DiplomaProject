package com.medical.demo.model;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class Message {
    public String message;
    public String fromUsername;
}