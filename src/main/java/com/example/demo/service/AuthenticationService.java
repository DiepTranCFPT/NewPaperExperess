package com.example.demo.service;

import com.example.demo.entity.User;

import com.example.demo.exception.AuthException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.GlobalException;

import com.example.demo.infor.Role;
import com.example.demo.iservice.IAuthenticationService;
import com.example.demo.model.Request.*;
import com.example.demo.model.Response.AccountResponse;
import com.example.demo.repository.AuthenticationRepository;

import com.example.demo.utils.OtherFunctions;
import com.example.demo.utils.SendMailUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private String VerifyCode;
    private User user;

    @Autowired
    public AuthenticationService(AuthenticationRepository authenticationRepository,
                                 TokenService tokenService,
                                 PasswordEncoder passwordEncoder,
                                 EmailService emailService) {
        this.authenticationRepository = authenticationRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());


    @Transactional
    @Override
    public User register(RegisterRequest registerRequest) {
        if (authenticationRepository.existsByEmail(registerRequest.getEmail())) {
            throw new AuthException("Email already in use");
        }
        VerifyCode = OtherFunctions.generateRandomNumberString();
        user = User.builder()
                .name(registerRequest.getName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .isEnable(true)
                .DataActivate(OtherFunctions.DateSystem())
                .role(Role.USER)
                .build();
        try {

            user.setAvata(OtherFunctions.UploadImg("avatadf.jpg"));

            emailService.sendMailVerification("Verifycode Regis account",
                    registerRequest.getEmail(),
                    VerifyCode,
                    SendMailUtils.Template(VerifyCode));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean verify(String verificationCode) {
        if (verificationCode.equals(VerifyCode)) {
            user = authenticationRepository.save(user);
            return true;
        }
        return false;
    }


    @Override
    public AccountResponse login(LoginRequest loginRequest) {
        user = authenticationRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new AuthException("Account not found with email: " + loginRequest.getEmail()));
        AccountResponse accountResponse;

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthException("Wrong Id Or Password");
        }

        String token = tokenService.generateToken(user);
        accountResponse = new AccountResponse(user);
        accountResponse.setToken(token);
        
        return accountResponse;
    }

    @Override
    public AccountResponse loginGoogle(LoginGoogleRequest loginGoogleRequest) {
//        AccountResponse accountResponse;
//        try {
//            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(loginGoogleRequest.getToken());
//            String email = firebaseToken.getEmail();
//            user = authenticationRepository.findByEmail(email);
//
//            if (user == null) {
//                user = new User();
//                user.setName(firebaseToken.getName());
//                user.setEmail(firebaseToken.getEmail());
//                user = authenticationRepository.save(user);
//
//            }
//            accountResponse = new AccountResponse(user);
//            String token = tokenService.generateToken(user);
//            accountResponse.setToken(token);
//
//        } catch (FirebaseAuthException e) {
//            logger.severe("Firebase authentication error: " + e.getMessage());
//            throw new BadRequestException("Invalid Google token");
//        }

        return null;
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
//        user = authenticationRepository.findByEmail(forgotPasswordRequest.getEmail());
//        if (user == null) {
//            throw new BadRequestException("Account not found");
//        }

    }

    @Override
    public User verifyForgotPassword(String verificationCode) {
        return null;
    }


    @Override
    public User resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<User> user = authenticationRepository.findByEmail(resetPasswordRequest.getEmail());
        user.ifPresent(user1 -> {


        });
        if (user == null) {
            throw new GlobalException("Not found email");
        }

//        if (!token.equals(resetPasswordRequest.getToken())) {
//            throw new GlobalException("Invalid token");
//        } else {
//            VerifyCode = OtherFunctions.generateRandomNumberString();
//            try {
//                emailService.sendMailVerification("Verifycode Regis account",
//                        resetPasswordRequest.getEmail(),
//                        VerifyCode,
//                        SendMailUtils.Template(VerifyCode));
//                return user;
//            } catch (MessagingException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return null;
    }


    @Override
    public User findById(String id) {
        return authenticationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account not found with id: " + id));
    }

    @Override
    @Transactional
    public User registerforGoogle(RegisterforGoogle GoogleAccount) {
        user = User.builder()
                .name(GoogleAccount.getDisplayName())
                .email(GoogleAccount.getEmail())
                .uid(GoogleAccount.getUid())
                .isEnable(true)
                .DataActivate(OtherFunctions.DateSystem())
                .role(Role.USER)
                .build();
        authenticationRepository.save(user);
        try {
            user = authenticationRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AuthException("TaiKhoanTontai ");
        }
        return user;
    }


}
