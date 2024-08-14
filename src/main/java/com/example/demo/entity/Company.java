package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String name;
    private String moreInfo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;


}

