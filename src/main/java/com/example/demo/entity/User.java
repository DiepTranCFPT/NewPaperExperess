package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   long id;
   String name;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   String password;

   @Column(unique = true)
   String phone;

   @Column(unique = true)
   String email;

   @ManyToOne
   @JoinColumn(name = "company_id")
   private Company company;

   @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
   private List<Article> articles;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<Rating> ratings;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<Comment> comments;

   private boolean enable;

   private String verificationCode;

}
