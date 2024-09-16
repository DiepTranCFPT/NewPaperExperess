package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String Content;

    private LocalDateTime time;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

    @PrePersist
    protected void createDateTime() {
        time = LocalDateTime.now();
    }

}
