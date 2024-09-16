package com.experess.news.model.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterforGoogle {
    private String displayName;
    private String photoURL;
    private String email;
    private String uid;
}
