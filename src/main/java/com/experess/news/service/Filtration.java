package com.experess.news.service;

import com.experess.news.entity.Article;
import com.experess.news.entity.User;
import com.experess.news.infor.Role;
import com.experess.news.iservice.IArticleService;
import com.experess.news.iservice.IAuthenticationService;
import com.experess.news.iservice.IFollowService;
import com.experess.news.repository.AuthenticationRepository;
import com.experess.news.repository.IArticleRepository;
import com.experess.news.repository.IFollowRepository;
import com.experess.news.utils.OtherFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;

/**
 * cac chuc nang loc
 */

@Service
public class Filtration {

    private final IAuthenticationService authenticationService;
    private final AuthenticationRepository authenticationRepository;
    private final IArticleService articleService;
    private final IArticleRepository iArticleRepository;
    private final IFollowService iFollowService;
    private final IFollowRepository iFollowRepository;

    @Autowired
    public Filtration(IAuthenticationService authenticationService,
                      AuthenticationRepository authenticationRepository,
                      IArticleService articleService,
                      IArticleRepository iArticleRepository,
                      IFollowService iFollowService,
                      IFollowRepository iFollowRepository) {
        this.articleService = articleService;
        this.authenticationRepository = authenticationRepository;
        this.authenticationService = authenticationService;
        this.iArticleRepository = iArticleRepository;
        this.iFollowService = iFollowService;
        this.iFollowRepository = iFollowRepository;
    }


    // lay danh sach bai viet theo ngay tao


    public List<String> getArticleListByDate(String publishedDate) {
        List<Article> articles = iArticleRepository.findAllByPublishedDate(publishedDate);
        return OtherFunctions.getListStringID(articles,
                Article::getId);
    }

    // lay danh sach bai viet theo tac gia
    // sort theo muc do anh huong + danh gia cac bai viet


    public List<String> getUserListByName(@NotNull String name) {

        List<User> getUser = authenticationRepository.findByNameContainingAndRole(name, Role.AUTHOR);

        Comparator<User> sortByFl = Comparator.comparingInt(user ->
                user.getFollowers() != null ? user.getFollowers().size() : 0
        ); // sort theo so followers
        getUser.sort(sortByFl);

        return OtherFunctions.getListStringID(getUser,
                User::getId);
    }

    // lay danh sach bai viet theo danh gia


    // lay danh sach bai viet theo tuong tac
    // lay danh sach bai moi nhat
    // lay danh sach bai viet theo chu de va theo ca ys tren
    // lay danh sach bai viet theo key
    // lay danh sach bai viet theo bao cao
    // lay danh sach bai viet theo
    // lay danh sach bai viet theo

}
