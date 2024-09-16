package com.experess.news.iservice;

import com.experess.news.entity.Follows;
import com.experess.news.model.Request.FollowRequest;
import com.experess.news.model.Response.UserReponseFl;

import java.util.List;

public interface IFollowService {

    Follows follows(FollowRequest followRequest);

    boolean unfollow(FollowRequest unfollowRequest);

    List<UserReponseFl> getFollows(String userID);

}
