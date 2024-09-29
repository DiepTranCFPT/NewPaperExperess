package com.experess.news.model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequest {
    private String idArticle;
    private String idUser;
    private String Content;
}
