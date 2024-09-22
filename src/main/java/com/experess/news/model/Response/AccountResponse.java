package com.experess.news.model.Response;

import com.experess.news.entity.User;
import com.experess.news.infor.Gender;
import com.experess.news.infor.Role;

import lombok.Getter;
import lombok.Setter;


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
    private Gender gender;
    private String describe;
    private String address;

    public AccountResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.avatar = user.getAvata();
        this.role = user.getRole();
        this.DataActivate = user.getDataActivate();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.describe = user.getDescription();
        this.address = user.getAddress();
    }
}

