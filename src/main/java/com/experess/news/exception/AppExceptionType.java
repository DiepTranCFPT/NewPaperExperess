package com.experess.news.exception;

public enum AppExceptionType {
    AUTH_EXCEPTION("Authentication failed"),
    VALIDATION_EXCEPTION("Validation error"),
    DATABASE_EXCEPTION("Database error"),
    RESOURCE_NOT_FOUND("Resource not found"),
    UNAUTHORIZED_EXCEPTION("Unauthorized access"),
    BAD_REQUEST_EXCEPTION("Bad request"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private final String message;

    AppExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
