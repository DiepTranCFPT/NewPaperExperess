package com.example.demo.model.Response;

import com.example.demo.entity.User;
import com.example.demo.infor.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
public class AccountResponse {

    private String id;
    private String name;
    private String email;
    private byte[] avatar;
    private Role role;
    private String DataActivate;
    private String phone;
    private String token;

    public AccountResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.avatar = user.getAvata();
        this.role = user.getRole();
        this.DataActivate = user.getDataActivate();
        this.phone = user.getPhone();
    }
}

