package com.example.demo.entity;


import com.example.demo.infor.Gender;
import com.example.demo.infor.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Account extends BaseObject{


   String name;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   String password;

   @Column(unique = true)
   String phone;

   @Column(unique = true)
   String email;



   private boolean enable;

   private String verificationCode;

   @ManyToOne
   private com.example.demo.entity.Company company;

   @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
   private List<com.example.demo.entity.Article> articles;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<com.example.demo.entity.Rating> ratings;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<com.example.demo.entity.Comment> comments;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<com.example.demo.entity.Report> reports;

   private String birthDate;
   // theo doi
   @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<com.example.demo.entity.Follows> followingUsers;

   // dang theo doi
   @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<com.example.demo.entity.Follows> followers;

   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<com.example.demo.entity.Experience> experiences;


   @Lob
   @Column(columnDefinition = "LONGBLOB")
   private byte[] avata;

   @Enumerated(EnumType.STRING)
   private Role role;

   @Enumerated(EnumType.STRING)
   private Gender gender;


   private String uid;

   @Column(length = 150)
   @Size(max = 150, message = "Describe cannot exceed 150 characters")
   private String description;

   private String address;

}
