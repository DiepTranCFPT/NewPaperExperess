package com.experess.news.model.Response.articlereponse;

import com.experess.news.entity.Article;
import com.experess.news.entity.Type;
import com.experess.news.utils.OtherFunctions;
import lombok.Getter;
import lombok.Setter;


/**
 * tra ve nhung y chinh cua bai viet su dung cho hien san pham khi tim kim va  show goi y
 */
@Getter
@Setter
public class ArticleResponseSum {
    private String id;
    private String title;
    private String content;
    private String publishedDate;
    private String createdDate;
    private String author;
    private Type type;
    private int ratings;
    private int access;

    public ArticleResponseSum(ArticleResponseDetails article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.publishedDate = article.getPublishedDate();
        this.createdDate = article.getCreatedDate();
        this.author = article.getAuthor();
        this.type = article.getType();
        this.ratings = article.getRatings();
        this.access = article.getAccess();
    }

    public ArticleResponseSum(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.publishedDate = article.getPublishedDate();
        this.createdDate = article.getCreatedDate();
        this.author = article.getAuthor().getName();
        this.type = article.getType();
        this.ratings = OtherFunctions.ratingMedium(article.getRatings());
        this.access = article.getAccess();
    }

}
