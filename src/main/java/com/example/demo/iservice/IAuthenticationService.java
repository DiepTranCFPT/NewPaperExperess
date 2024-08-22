package com.example.demo.iservice;

import com.example.demo.entity.User;
import com.example.demo.model.Request.*;
import com.example.demo.model.Response.AccountResponse;

public interface IAuthenticationService {
    User register(RegisterRequest registerRequest);
    boolean verify(String verificationCode);
    AccountResponse login(LoginRequest loginRequest);
    AccountResponse loginGoogle(LoginGoogleRequest loginGoogleRequest);
    void forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    User resetPassword(ResetPasswordRequest resetPasswordRequest);
    User findById(String id);
    User registerforGoogle(RegisterforGoogle GoogleAccount);
    User verifyForgotPassword(String verificationCode);
}
