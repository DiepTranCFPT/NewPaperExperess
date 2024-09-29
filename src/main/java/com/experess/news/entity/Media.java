package com.experess.news.entity;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media extends BaseObject{

    @Lob
    private byte[] data;

    private String type;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}

