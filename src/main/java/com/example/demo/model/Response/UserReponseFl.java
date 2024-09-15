package com.example.demo.model.Response;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReponseFl {
    private String id;
    private String fullName;
    private byte[] avatar;

    public UserReponseFl(User user) {
        this.id = user.getId();
        this.fullName = user.getName();
        this.avatar = user.getAvata();
    }
}
