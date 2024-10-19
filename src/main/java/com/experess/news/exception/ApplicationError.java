package com.experess.news.exception;

public enum ApplicationError {
    // Lỗi xác thực
    AUTHENTICATION_FAILED("Lỗi xác thực người dùng thất bại"),
    AUTHORIZATION_FAILED("Lỗi quyền truy cập bị từ chối"),

    // Lỗi dữ liệu
    ENTITY_NOT_FOUND("Không tìm thấy đối tượng"),
    DUPLICATE_ENTITY("Dữ liệu bị trùng lặp"),
    INVALID_DATA("Dữ liệu không hợp lệ"),
    DATA_INTEGRITY_VIOLATION("Lỗi vi phạm toàn vẹn dữ liệu"),

    // Lỗi hệ thống
    INTERNAL_SERVER_ERROR("Lỗi máy chủ nội bộ"),
    SERVICE_UNAVAILABLE("Dịch vụ không khả dụng"),
    BAD_REQUEST("Yêu cầu không hợp lệ"),
    METHOD_NOT_ALLOWED("Phương thức không được phép"),
    UNSUPPORTED_MEDIA_TYPE("Loại dữ liệu không được hỗ trợ"),
    TIMEOUT_ERROR("Yêu cầu bị quá thời gian"),

    // Lỗi cơ sở dữ liệu
    DATABASE_CONNECTION_FAILED("Kết nối cơ sở dữ liệu thất bại"),
    CONSTRAINT_VIOLATION("Lỗi ràng buộc cơ sở dữ liệu"),

    // Lỗi liên quan đến tệp
    FILE_NOT_FOUND("Không tìm thấy tệp"),
    FILE_UPLOAD_FAILED("Tải lên tệp thất bại"),

    // Lỗi khác
    UNKNOWN_ERROR("Lỗi không xác định");

    private final String message;

    ApplicationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

