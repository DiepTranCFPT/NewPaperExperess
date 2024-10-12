package com.experess.news.service;

import com.experess.news.entity.Article;
import com.experess.news.entity.Follows;
import com.experess.news.entity.User;
import com.experess.news.infor.Role;
import com.experess.news.iservice.IArticleService;
import com.experess.news.iservice.IAuthenticationService;
import com.experess.news.iservice.IFollowService;
import com.experess.news.model.Response.articlereponse.ArticleResponseDetails;
import com.experess.news.model.Response.articlereponse.ArticleResponseSum;
import com.experess.news.repository.AuthenticationRepository;
import com.experess.news.repository.IArticleRepository;
import com.experess.news.repository.IFollowRepository;
import com.experess.news.utils.OtherFunctions;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    public List<String> getArticleListByDate(LocalDateTime publishedDate) {
        List<Article> articles = iArticleRepository.findAllByTimeCreated(publishedDate);
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
    public List<ArticleResponseSum> getArticleListByRating(@NotNull List<ArticleResponseDetails> articleList) {

        Comparator<ArticleResponseDetails> sortByRating = Comparator.comparingInt(ArticleResponseDetails::getRatings);
        articleList.sort(sortByRating);

        return OtherFunctions.getListObject(articleList, ArticleResponseSum::new);
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

    public List<ArticleResponseSum> A(@NotNull ArticleResponseDetails articleResponse) {

        List<Article> articles = iArticleRepository.findByTitleContaining(articleResponse.getTitle()); // lay cac bai viet co title tuong tu bai viet dang xem

        User author = authenticationRepository.findById(articleResponse.getAuthor())
                .orElseThrow((() -> new RuntimeException("No author"))); // lay ten tac gia cua bai viet dang xem

        if (author != null)
            articles.addAll(iArticleRepository.findByAuthor_NameContaining(author.getName())); // tim bai viet co tac gia ten tuong duong


//        List<Article> articleListbyRating = iArticleRepository.
//                findByRatingsBetween(articleResponse.getRatings(), 5); // lay danh sach cac bai viet co luoc danh gia cao.

        List<Article> articleListInDate = iArticleRepository.findAllByTimeCreated(LocalDateTime.now()); // lay bai viet trong ngay.

        articles.addAll(articleListInDate);

        ///
        ///
        ///


        return null;
    }


    // nhung tac gia co suc anh huong va viet ve chu de tuong tu
    public List<ArticleResponseSum> getListArticleBySameTitle(@NotNull String articleResponseTitle) {

        List<Article> articles = iArticleRepository.findByTitleContaining(articleResponseTitle); // lay cac bai viet co title tuong tu bai viet dang xem

        List<ArticleResponseSum> converList = OtherFunctions.getListObject(articles, ArticleResponseSum::new);

        Comparator<ArticleResponseSum> sortByRating = Comparator.comparingInt(ArticleResponseSum::getRatings).reversed();
        converList.sort(sortByRating);

        return converList;
    }
    // tac gia co ten tuong tu

    public List<ArticleResponseSum> getListArticleByAuthorName(@NotNull String nameAuthor) {

        User author = authenticationRepository.findById(nameAuthor)
                .orElseThrow((() -> new RuntimeException("author not found")));
        // lay ten tac gia cua bai viet dang xem
        if (author != null)
            throw new RuntimeException("author not found");

        List<Article> articles = iArticleRepository.findByAuthor_NameContaining(author.getName());

        List<ArticleResponseSum> converList = OtherFunctions.getListObject(articles, ArticleResponseSum::new);

        Comparator<ArticleResponseSum> sortByRating = Comparator.comparingInt(ArticleResponseSum::getRatings).reversed();
        converList.sort(sortByRating);
        return converList;
    }


    // tac gia va bai viet ma nhung nguoi ban co the biet

    public List<ArticleResponseSum> getListAuthorFollow(String idUser){

        User user = authenticationRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("user not found"));

        List<Follows> follows = user.getFollowers();

        List<User> followUser = List.copyOf(follows.stream().map(Follows::getFollowing).toList());

        return null;
    }


    //


}
