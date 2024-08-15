package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String moreInfo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;
}

