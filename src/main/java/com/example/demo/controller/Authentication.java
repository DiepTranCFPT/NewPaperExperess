package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.model.Request.*;
import com.example.demo.model.Response.AccountResponse;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.EmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("api")
public class Authentication {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @Autowired
    public Authentication(AuthenticationService authenticationService,
                          EmailService emailService) {
        this.authenticationService = authenticationService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        User user = authenticationService.register(registerRequest);
        return user != null ? ResponseEntity.ok("ok") : ResponseEntity.ok("error");
    }

    @PostMapping("/registergg")
    public ResponseEntity<User> registerGG(@RequestBody RegisterforGoogle registerGGRequest) {
        User user = authenticationService.registerforGoogle(registerGGRequest);
        return user != null ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

//    @GetMapping("/verify")
//    public ResponseEntity<Void> verifyUser(@RequestParam("code") String code) {
//        boolean verified = authenticationService.verify(code);
//        if (verified) {
//            String successUrl = "http://booking88.online/verify_success";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(URI.create(successUrl));
//            return new ResponseEntity<>(headers, HttpStatus.FOUND);
//        } else {
//            String failureUrl = "http://booking88.online/verify_failed";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(URI.create(failureUrl));
//            return new ResponseEntity<>(headers, HttpStatus.FOUND);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<AccountResponse> login(@RequestBody LoginRequest loginRequest) {
        AccountResponse account = authenticationService.login(loginRequest);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/login-google")
    public ResponseEntity<AccountResponse> loginGg(@RequestBody LoginGoogleRequest loginGoogleRequest) {
        return ResponseEntity.ok(authenticationService.loginGoogle(loginGoogleRequest));
    }

    @PostMapping("/forgot-password")
    public void forgotpassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        authenticationService.forgotPassword(forgotPasswordRequest);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(
            @Valid @NotNull
            @RequestParam(value = "token") String token, @RequestBody ResetPasswordRequest resetPasswordRequest) {

        if (authenticationService.resetPassword(resetPasswordRequest) == 1) {
            if (token.equals(resetPasswordRequest.getToken())) {
                String successUrl = "http://booking88.online/verify_success";
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(successUrl));
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            }

        } else {

            String successUrl = "http://booking88.online/verify_failed";
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(successUrl));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }
        return null;
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<User> getAccountById(@NotNull @Valid @PathVariable(value = "id") String id) {
        return ResponseEntity.ok(authenticationService.findById(id));
    }


    @PostMapping("/verify/{code}")
    public ResponseEntity<Boolean> verifyAccount(@NotNull @Valid @PathVariable(value = "code") String code) {
        return ResponseEntity.ok(authenticationService.verify(code));
    }


//    @PostMapping("/account")
//    public ResponseEntity<String> getAccountByPhone(@RequestParam String test) {
//        return ResponseEntity.ok(test);
//    }
}
