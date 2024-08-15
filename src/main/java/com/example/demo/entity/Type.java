package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String typeName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "type")
    private Article article;

    // getters and setters
}

