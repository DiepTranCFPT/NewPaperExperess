package com.example.demo.service;

import com.example.demo.entity.Follows;
import com.example.demo.iservice.IFollowService;
import com.example.demo.model.Request.FollowRequest;
import com.example.demo.repository.IFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService implements IFollowService {

    private final IFollowRepository iFollowRepository;

    @Autowired
    public FollowService(IFollowRepository iFollowRepository) {
        this.iFollowRepository = iFollowRepository;
    }


    @Override
    public Follows follows(FollowRequest followRequest) {
        return null;
    }
}
