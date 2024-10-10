package com.experess.news.securityconfig;


import com.experess.news.iservice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {


    private static final Logger LOG = LoggerFactory.getLogger(AppRunner.class);

    private final IArticleService articleService;
    private final IAuthenticationService I_AUTHENTICATION_SERVIC;
    public final IEmailService I_EMAIL_SERVICE;
    public final IFollowService I_FOLLOW_SERVICE;
    public final IRatingService I_RATING_SERVICE;
    public final ITypeService I_TYPE_SERVICE;


    public AppRunner(IArticleService articleService,
                     IAuthenticationService I_AUTHENTICATION_SERVIC,
                     IEmailService I_EMAIL_SERVICE,
                     IFollowService I_FOLLOW_SERVICE,
                     IRatingService I_RATING_SERVICE,
                     ITypeService I_TYPE_SERVICE
    ) {
        this.I_AUTHENTICATION_SERVIC = I_AUTHENTICATION_SERVIC;
        this.articleService = articleService;
        this.I_EMAIL_SERVICE = I_EMAIL_SERVICE;
        this.I_FOLLOW_SERVICE = I_FOLLOW_SERVICE;
        this.I_RATING_SERVICE = I_RATING_SERVICE;
        this.I_TYPE_SERVICE = I_TYPE_SERVICE;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
