package com.experess.news.controller;


import com.experess.news.entity.User;
import com.experess.news.iservice.IAuthenticationService;
import com.experess.news.model.Request.*;
import com.experess.news.model.Response.AccountResponse;
import com.experess.news.service.FirebaseService;
import com.experess.news.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("api")
public class Authentication {

    private final IAuthenticationService authenticationService;
    private final FirebaseService firebaseService;
    private final TokenService tokenService;

    @Autowired
    public Authentication(IAuthenticationService authenticationService
            , FirebaseService firebaseService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.firebaseService = firebaseService;
        this.tokenService = tokenService;
    }


    @Operation(summary = "Create a new Account",
            description = "returns String status information"
            , security = {@SecurityRequirement(name = "bearer-key")}
            , tags = {"account"})
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = authenticationService.register(registerRequest);
        return user != null ? ResponseEntity.ok("ok") : ResponseEntity.ok("error");
    }

    @Operation(summary = "Create a new Account Google",
            description = "returns String status information"
            , security = {@SecurityRequirement(name = "bearer-key")}
            , tags = {"account"})
    @PostMapping("/registergg")
    public ResponseEntity<String> registerGG(@RequestBody RegisterforGoogle registerGGRequest) {
        User user = authenticationService.registerforGoogle(registerGGRequest);
        return user != null ? ResponseEntity.ok("ok") : ResponseEntity.ok("error");
    }

    @Operation(summary = "Login a new Account",
            description = "returns AccountResponse"
            //,security = {@SecurityRequirement(name = "bearer-key")}
            , tags = {"loginRequest"})
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
    public void forgotpassword(@RequestParam @NotNull @Valid String email) {
        authenticationService.forgotPassword(email);
    }

    @PostMapping("/send-verification-email/{code}")
    public ResponseEntity<Boolean> sendVerificationEmail(@PathVariable(value = "code") @NotNull String veString) {
        return ResponseEntity.ok(authenticationService.verifyToforgot(veString));
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<User> forgotpasswordKey(@RequestParam String password) {
        return ResponseEntity.ok(authenticationService.changePassword(password));
    }

    @PostMapping("/resetpassword")
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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        try {
            String token = request.get("token");
            tokenService.revokeToken(token);
            return ResponseEntity.ok("Token revoked successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to revoke token");
        }
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<?> editAccount(@NotNull @RequestBody UserRequest request) {
        boolean isEdit = authenticationService.editUser(request);
        return ResponseEntity.ok(isEdit);
    }
}
