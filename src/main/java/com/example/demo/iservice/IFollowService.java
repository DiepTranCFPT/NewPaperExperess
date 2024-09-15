package com.example.demo.iservice;

import com.example.demo.entity.Follows;
import com.example.demo.model.Request.FollowRequest;
import com.example.demo.model.Response.UserReponseFl;

import java.util.List;

public interface IFollowService {
    Follows follows(FollowRequest followRequest);

    boolean unfollow(FollowRequest unfollowRequest);

    List<UserReponseFl> getFollow(String userID);
}
