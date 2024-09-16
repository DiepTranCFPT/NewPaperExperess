package com.experess.news.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class RevokedToken {
    @Id
    private String tokenId;

    private Date expirationDate;

}

