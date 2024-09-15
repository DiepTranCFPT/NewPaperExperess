package com.example.demo.iservice;

import com.example.demo.entity.Follows;
import com.example.demo.model.Request.FollowRequest;

public interface IFollowService {
    Follows follows(FollowRequest followRequest);
}
