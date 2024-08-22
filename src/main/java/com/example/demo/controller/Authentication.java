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
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = authenticationService.register(registerRequest);
        return user != null ? ResponseEntity.ok("ok") : ResponseEntity.ok("error");
    }

    @PostMapping("/registergg")
    public ResponseEntity<String> registerGG(@RequestBody RegisterforGoogle registerGGRequest) {
        User user = authenticationService.registerforGoogle(registerGGRequest);
        return user != null ? ResponseEntity.ok("ok") : ResponseEntity.ok("error");
    }


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
    public ResponseEntity<Boolean> resetPassword(
            @Valid @NotNull
            @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.ok(authenticationService.resetPassword(resetPasswordRequest));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<User> getAccountById(@NotNull @Valid @PathVariable(value = "id") String id) {
        return ResponseEntity.ok(authenticationService.findById(id));
    }


    @PostMapping("/verify/{code}")
    public ResponseEntity<Boolean> verifyAccount(@NotNull @Valid @PathVariable(value = "code") String code) {
        return ResponseEntity.ok(authenticationService.verify(code));
    }
}
