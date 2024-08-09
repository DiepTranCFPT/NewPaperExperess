package com.example.demo.model.Response;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse extends User {

    private long idLocation;
    private long idLocationStaff;
    private String token;

}
