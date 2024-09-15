package com.example.demo.repository;

import com.example.demo.entity.Follows;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;


@Repository
public interface IFollowRepository extends JpaRepository<Follows, String> {


    List<User> findAllByFollower_Id(String followerId);

//    default List<String> getListfollowerID(String followerId) {
//        return List.copyOf(findAllByFollower_Id(followerId)
//                .stream().map(User::getId)
//                .toList());
//    }
//

    default List<String> getListfollowByList(String followerId,
                                             Function<String, List<User>> listListFunction) {
        return List.copyOf(listListFunction.apply(followerId)
                .stream().map(User::getId)
                .toList());
    }
}
