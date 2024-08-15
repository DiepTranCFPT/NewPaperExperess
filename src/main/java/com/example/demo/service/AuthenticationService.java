package com.example.demo.service;

import com.example.demo.entity.User;

import com.example.demo.exception.AuthException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.GlobalException;

import com.example.demo.model.EmailDetail;
import com.example.demo.model.Request.*;
import com.example.demo.model.Response.AccountResponse;
import com.example.demo.repository.AuthenticationRepository;
import com.example.demo.utils.AccountUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;


    @Autowired
    AccountUtils accountUtils;


    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    @Transactional
    public User register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setPhone(registerRequest.getPhone());
        user.setEmail(registerRequest.getEmail());
        user.setEnable(false);
        user.setVerificationCode(UUID.randomUUID().toString());

        authenticationRepository.save(user);

        try {
            user = authenticationRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AuthException("Duplicate");
        }

//        EmailDetail emailDetail = new EmailDetail();
//        emailDetail.setRecipient(registerRequest.getEmail());
//        emailDetail.setSubject("Verify your registration");
//        emailDetail.setName(registerRequest.getName());
//        String verifyURL = "http://booking88.online/api/verify?code="+account.getVerificationCode();
//        emailDetail.setLink(verifyURL);
//        emailDetail.setButtonValue("Verify Email");
//        emailService.sendMailTemplate(emailDetail);
        return user;
    }

    public boolean verify(String verificationCode) {
        User user = authenticationRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnable()) {
            return false;
        } else {
            user.setEnable(true);
//            account.setStatus(AccoutStatus.ACTIVE);
            authenticationRepository.save(user);
            return true;
        }
    }

    public AccountResponse login(LoginRequest loginRequest) {
        var account = authenticationRepository.findByEmail(loginRequest.getEmail());
        if (account == null) {
            throw new AuthException("Account not found with email: " + loginRequest.getEmail());
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
            throw new AuthException("Wrong Id Or Password");
        }

//        if (account.getStatus().equals(AccoutStatus.DELETED)) {
//            throw new AuthException("Account deleted");
//        }

        // Check if the account is verified
        if (!account.isEnable()) {
            throw new AuthException("Account not verified. Please check your email to verify your account.");
        }

        String token = tokenService.generateToken(account);
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setEmail(account.getEmail());
        accountResponse.setToken(token);

        accountResponse.setName(account.getName());
        accountResponse.setPhone(account.getPhone());


        return accountResponse;
    }


    public AccountResponse loginGoogle(LoginGoogleRequest loginGoogleRequest) {
        AccountResponse accountResponse = new AccountResponse();
        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(loginGoogleRequest.getToken());
            String email = firebaseToken.getEmail();
            User user = authenticationRepository.findByEmail(email);

            if (user == null) {
                user = new User();
                user.setName(firebaseToken.getName());
                user.setEmail(firebaseToken.getEmail());


                user = authenticationRepository.save(user);

            }


            accountResponse.setId(user.getId());
            accountResponse.setName(user.getName());
            accountResponse.setEmail(user.getEmail());

            String token = tokenService.generateToken(user);
            accountResponse.setToken(token);

        } catch (FirebaseAuthException e) {
            logger.severe("Firebase authentication error: " + e.getMessage());
            throw new BadRequestException("Invalid Google token");
        }

        return accountResponse;
    }






    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = authenticationRepository.findByEmail(forgotPasswordRequest.getEmail());
        if (user == null) {
            throw new BadRequestException("Account not found");
        }

        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient(forgotPasswordRequest.getEmail());
        emailDetail.setSubject("Reset Password for account " + forgotPasswordRequest.getEmail() + "!!!");
        emailDetail.setMsgBody(""); // You might want to add a meaningful message here
        emailDetail.setButtonValue("Reset Password");
        emailDetail.setLink("http://booking88.online/reset-password?token=" + tokenService.generateToken(user));
        emailDetail.setName(user.getName());

        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMailTemplateForgot(emailDetail);
            }
        };

        new Thread(r).start();
    }


    public int resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = authenticationRepository.findByEmail(resetPasswordRequest.getEmail());

        if (user == null) {
            throw new GlobalException("Not found email");
        }
        String token = tokenService.generateToken(user);
        // Check if the token matches
        if (!token.equals(resetPasswordRequest.getToken())) {
            throw new GlobalException("Invalid token");
        }else {
            user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            authenticationRepository.save(user);
            return 1;
        }

    }
//    public Account deleteAccount(long id) {
//        Account account = authenticationRepository.findById(id).orElseThrow(() -> new AuthException("Can not find account"));;
//        account.setStatus(AccoutStatus.DELETED);
//        return authenticationRepository.save(account);
//    }


    public User findById(UUID id) {
        User user = authenticationRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        return user;
    }
}
