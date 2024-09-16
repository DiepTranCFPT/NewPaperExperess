package com.experess.news.model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowRequest {
    private String id;
    private String followingID;
}
