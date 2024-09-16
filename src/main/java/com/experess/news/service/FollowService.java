package com.experess.news.service;

import com.experess.news.entity.Follows;
import com.experess.news.entity.User;
import com.experess.news.iservice.IAuthenticationService;
import com.experess.news.iservice.IFollowService;
import com.experess.news.model.Request.FollowRequest;
import com.experess.news.model.Response.UserReponseFl;
import com.experess.news.repository.IFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserReponseFl> getFollows(String userID) {
        return iFollowRepository.getListfollowByUser(userID,
                authenticationService::findById);
    }





}
