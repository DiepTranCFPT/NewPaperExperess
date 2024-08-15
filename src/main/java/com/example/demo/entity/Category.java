package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Type> types;

}

