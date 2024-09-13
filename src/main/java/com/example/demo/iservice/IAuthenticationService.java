package com.example.demo.iservice;

import com.example.demo.entity.User;
import com.example.demo.model.Request.*;
import com.example.demo.model.Response.AccountResponse;


public interface IAuthenticationService {
    User register(RegisterRequest registerRequest);
    boolean verify(String verificationCode);
    AccountResponse login(LoginRequest loginRequest);
    AccountResponse loginGoogle(LoginGoogleRequest loginGoogleRequest);
    void forgotPassword(String email);
    boolean resetPassword(ResetPasswordRequest resetPasswordRequest);
    User findById(String id);
    User registerforGoogle(RegisterforGoogle GoogleAccount);
    User changePassword(String newPassword);
    boolean verifyToforgot(String verificationCode);
    boolean reportUser(ReportRequest reportRequest);

}
