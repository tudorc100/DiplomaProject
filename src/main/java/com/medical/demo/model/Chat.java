package com.medical.demo.model;

import lombok.*;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@RequiredArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String author;
    @Column
    private String text;
    @Column
    private LocalDateTime time;

    public Chat(LocalDateTime now, String tudor, String test) {
        this.time=now;
        this.author=tudor;
        this.text=test;
    }
}
