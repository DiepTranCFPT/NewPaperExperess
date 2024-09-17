package com.experess.news.service;

import com.experess.news.entity.Article;
import com.experess.news.entity.User;
import com.experess.news.infor.Role;
import com.experess.news.iservice.IArticleService;
import com.experess.news.iservice.IAuthenticationService;
import com.experess.news.iservice.IFollowService;
import com.experess.news.model.Response.ArticleResponse;
import com.experess.news.repository.AuthenticationRepository;
import com.experess.news.repository.IArticleRepository;
import com.experess.news.repository.IFollowRepository;
import com.experess.news.utils.OtherFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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


    /**
     * sort by follow
     *
     * @param name
     * @return
     */
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


    /**
     * sort by rating
     *
     * @param articleList
     * @return
     */
    public List<ArticleResponse> getArticleListByRating(@NotNull List<ArticleResponse> articleList) {

        Comparator<ArticleResponse> sortByRating = Comparator.comparingInt(ArticleResponse::getRatings);
        articleList.sort(sortByRating);

        return articleList;
    }


    // lay danh sach bai viet theo tuong tac


    // lay danh sach bai moi nhat
    // lay danh sach bai viet theo chu de va theo ca ys tren
    // lay danh sach bai viet theo key
    // lay danh sach bai viet theo bao cao
    // lay danh sach bai viet theo
    // lay danh sach bai viet theo


    // DE XUAT
    // xem 1 bai viet voi noi dung noi ve 1 "chu de" tuong tu

    public List<ArticleResponse> A(@NotNull ArticleResponse articleResponse) {

        List<Article> articles = iArticleRepository.findByTitleContaining(articleResponse.getTitle());
        User author = authenticationRepository.findById(articleResponse.getAuthor())
                .orElseThrow((() -> new RuntimeException("No author")));




        return null;
    }


    // nhung tac gia co suc anh huong va viet ve chu de tuong tu
    // tac gia co ten tuong tu
    // tac gia va bai viet ma nhung nguoi ban co the biet
    //


}
