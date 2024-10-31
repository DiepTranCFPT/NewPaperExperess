package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.iService.IArticleService;
import com.example.demo.model.Request.ArticleRequest;
import com.example.demo.model.Response.ArticleResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class ArticleAPI {

    private final IArticleService articleService;

    @Autowired
    public ArticleAPI(IArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/writeArticle")
    public ResponseEntity<?> write (@RequestBody ArticleRequest articleRequest){
        Article article = articleService.writeArticle(articleRequest);
        if (article != null)
            return ResponseEntity.status(200).body("Success");
        else
            return ResponseEntity.status(400).body("Khong hop le");
    }

    @GetMapping("/readArticle")
    public ResponseEntity <ArticleResponseDetails> read (String id){
        ArticleResponseDetails articleResponseDetails = articleService.readArticle(id);
        return ResponseEntity.ok(articleResponseDetails);
    }

    @PutMapping("editArcticle")
    public ResponseEntity<?> edit (String id,ArticleRequest articleRequest){
        Article article = articleService.editArticle(id, articleRequest);
        if (article == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Khong hop le");
        }
        else return ResponseEntity.status(200).body("suceess");
    }
    @GetMapping("/filterByType")
    public ResponseEntity<List<String>> filterByType (String type){
        List<String> filteredTypes  = articleService.filterByType(type);
        return ResponseEntity.ok(filteredTypes);
    }
    @GetMapping("/findByKeyWord")
    public ResponseEntity<List<String>> findKeyWord(String keyword){
        List<String> keyword1 = articleService.searchByKey(keyword);
        return ResponseEntity.ok(keyword1);
    }

}
