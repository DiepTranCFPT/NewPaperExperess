package com.experess.news.iservice;

import com.experess.news.entity.Report;
import com.experess.news.entity.User;
import com.experess.news.model.Request.*;
import com.experess.news.model.Response.AccountResponse;


public interface IAuthenticationService {
    default User register(RegisterRequest registerRequest) {
        return null;
    }

    default boolean verify(String verificationCode) {
        return false;
    }

    default AccountResponse login(LoginRequest loginRequest) {
        return null;
    }

    default AccountResponse loginGoogle(LoginGoogleRequest loginGoogleRequest) {
        return null;
    }

    default void forgotPassword(String email) {
    }

    default boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
        return false;
    }

    User findById(String id);

    default User registerforGoogle(RegisterforGoogle GoogleAccount) {
        return null;
    }

    default User changePassword(String newPassword) {
        return null;
    }

    default boolean verifyToforgot(String verificationCode) {
        return false;
    }

    Report reportUser(ReportRequest reportRequest);

    boolean editUser(UserRequest user);

}
