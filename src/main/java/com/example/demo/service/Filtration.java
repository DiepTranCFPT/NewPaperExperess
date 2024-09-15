package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.iservice.IArticleService;
import com.example.demo.iservice.IAuthenticationService;
import com.example.demo.iservice.IFollowService;
import com.example.demo.repository.AuthenticationRepository;
import com.example.demo.repository.IArticleRepository;
import com.example.demo.repository.IFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * - loc theo ngay
     * - loc theo tuong tac ( cmt , vote,...)
     * @param publishedDate
     * @return
     */

    public List<String> getArticleListForDate(String publishedDate){
        List<Article> articles = iArticleRepository.findAllByPublishedDate(publishedDate);

        return null;
    }



    // lay danh sach bai viet theo tac gia
    // lay danh sach bai viet theo danh gia
    // lay danh sach bai viet theo tuong tac
    // lay danh sach bai moi nhat
    // lay danh sach bai viet theo chu de va theo ca ys tren
    // lay danh sach bai viet theo key
    // lay danh sach bai viet theo bao cao
    // lay danh sach bai viet theo
    // lay danh sach bai viet theo

}
