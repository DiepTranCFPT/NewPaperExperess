package com.example.demo.service;

import com.example.demo.entity.Follows;
import com.example.demo.entity.User;
import com.example.demo.iservice.IAuthenticationService;
import com.example.demo.iservice.IFollowService;
import com.example.demo.model.Request.FollowRequest;
import com.example.demo.model.Response.UserReponseFl;
import com.example.demo.repository.IFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService implements IFollowService {

    private final IFollowRepository iFollowRepository;
    private final IAuthenticationService authenticationService;

    @Autowired
    public FollowService(IFollowRepository iFollowRepository,
                         IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.iFollowRepository = iFollowRepository;
    }


    @Override
    public Follows follows(FollowRequest followRequest) {

        User user = authenticationService.findById(followRequest.getId());
        User userToFollow = authenticationService.findById(followRequest.getFollowingID());

        if (user != null && userToFollow != null)
            return iFollowRepository.save(Follows.builder()
                    .follower(user)
                    .following(userToFollow)
                    .build());
        else
            throw new RuntimeException("User not found");

    }

    @Override
    public boolean unfollow(FollowRequest unfollowRequest) {

        User user = authenticationService.findById(unfollowRequest.getId());
        User userToUnfollow = authenticationService.findById(unfollowRequest.getFollowingID());
        if (user != null && userToUnfollow != null) {
            Follows follows = iFollowRepository.findByFollower_IdAndFollowing_Id(user.getId(), userToUnfollow.getId());
            if (follows != null) {
                iFollowRepository.delete(follows);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<UserReponseFl> getFollow(String userID) {
        User user = authenticationService.findById(userID);
        if (user != null) {
            List<Follows> followsList = user.getFollowingUsers();
            return followsList.stream().map(follows ->
                            new UserReponseFl(follows.getFollowing()))
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("user not found");
    }


}
