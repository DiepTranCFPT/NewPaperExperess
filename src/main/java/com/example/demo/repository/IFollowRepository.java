package com.example.demo.repository;

import com.example.demo.entity.Follows;
import com.example.demo.entity.User;
import com.example.demo.model.Response.UserReponseFl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Repository
public interface IFollowRepository extends JpaRepository<Follows, String> {


//    List<User> findAllByFollower_Id(String followerId);


    default List<UserReponseFl> getListfollowByUser(String followerId,
                                                    Function<String, User> listListFunction) {
        User user = listListFunction.apply(followerId);
        if (user != null) {
            List<Follows> followsList = user.getFollowingUsers();
            return followsList.stream().map(follows ->
                            new UserReponseFl(follows.getFollowing()))
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("user not found");
    }

    Follows findByFollower_IdAndFollowing_Id(String followerId, String followingId);


}
