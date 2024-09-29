package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    protected LocalDateTime TimeCreated;

    protected LocalDateTime TimeUpdatedLast;

    protected LocalDateTime TimeDeleted;


    // khi tao 1 doi tuong moi tg se duoc tu dong lu vao voi ngay gio he thong
    @PrePersist
    protected void createDateTime() {
        TimeCreated = LocalDateTime.now();
    }


    // tu dong cap nhat tg khi obj duoc thai doi
    @PreUpdate
    protected void updateDateTime() {
        TimeUpdatedLast = LocalDateTime.now();
    }
}
