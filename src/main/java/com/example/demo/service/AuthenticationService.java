package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.AuthException;
import com.example.demo.infor.Role;
import com.example.demo.iservice.IAuthenticationService;
import com.example.demo.model.Request.*;
import com.example.demo.model.Response.AccountResponse;
import com.example.demo.repository.AuthenticationRepository;

import com.example.demo.utils.OtherFunctions;
import com.example.demo.utils.SendMailUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @Transactional
    @Override
    public User register(RegisterRequest registerRequest) {
        if (authenticationRepository.existsByEmail(registerRequest.getEmail()))
            throw new AuthException("Email already in use");

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
            authenticationRepository.save(user);
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public AccountResponse login(LoginRequest loginRequest) {
        user = authenticationRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new AuthException("Account not found with email: " + loginRequest.getEmail()));
        AccountResponse accountResponse;

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new AuthException("Wrong Id Or Password");


        String token = tokenService.generateToken(user);
        accountResponse = new AccountResponse(user);
        accountResponse.setToken(token);

        return accountResponse;
    }

    @Override
    @Transactional
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
    @Transactional
    public void forgotPassword(String email) {
        user = authenticationRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("Account not found with email: " + email));

        VerifyCode = OtherFunctions.generateRandomNumberString();

        try {
            emailService.sendMailVerification("Verifycode forgot account",
                    email,
                    VerifyCode,
                    SendMailUtils.Template(VerifyCode));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional
    public User changePassword(String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return authenticationRepository.save(user);
    }

    @Override
    public boolean verifyToforgot(String verificationCode) {
        return verificationCode.equals(VerifyCode);
    }

    @Override
    @Transactional
    public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
        user = authenticationRepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(() -> new AuthException("Account not found with email: " + resetPasswordRequest.getEmail()));

        if (!passwordEncoder.matches(resetPasswordRequest.getOldPassword(), user.getPassword()))
            throw new AuthException("Wrong password");

        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        authenticationRepository.save(user);

        return true;
    }


    @Override
    public User findById(String id) {
        return authenticationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account not found with id: " + id));
    }

    @Override
    @Transactional
    public User registerforGoogle(RegisterforGoogle GoogleAccount) {
        if (authenticationRepository.existsByUid(GoogleAccount.getUid()))
            throw new AuthException("TaiKhoanTontai ");

        user = User.builder()
                .name(GoogleAccount.getDisplayName())
                .email(GoogleAccount.getEmail())
                .uid(GoogleAccount.getUid())
                .isEnable(true)
                .DataActivate(OtherFunctions.DateSystem())
                .role(Role.USER)
                .build();
        authenticationRepository.save(user);
        return user;
    }

    public BufferedImage getimg(String id){
        user = authenticationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account not found with id: " + id));
        try {
            return OtherFunctions.convertByteArrayToImage(user.getAvata());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
