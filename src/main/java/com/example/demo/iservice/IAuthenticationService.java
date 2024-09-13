package com.example.demo.iservice;

import com.example.demo.entity.User;
import com.example.demo.model.Request.*;
import com.example.demo.model.Response.AccountResponse;


public interface IAuthenticationService {
    default User register(RegisterRequest registerRequest) {return null;}

    default boolean verify(String verificationCode) { return false;}

    default AccountResponse login(LoginRequest loginRequest) {return null;}

    default AccountResponse loginGoogle(LoginGoogleRequest loginGoogleRequest) {return null;}

    default void forgotPassword(String email) {}

    default boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {return false;}

    default User findById(String id) {return null;}

    default User registerforGoogle(RegisterforGoogle GoogleAccount) {return null;}

    default User changePassword(String newPassword) {return null;}

    default boolean verifyToforgot(String verificationCode) {return false;}

    default boolean reportUser(ReportRequest reportRequest) {return false;}

}
