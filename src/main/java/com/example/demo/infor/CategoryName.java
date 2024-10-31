package com.example.demo.infor;

public enum CategoryName {
    THOI_SU("Thời sự"),
    THE_GIOI("Thế Giới"),
    VIDEO("Video"),
    GIAI_TRI("Giải trí"),
    THE_THAO("Thể Thao"),
    SUC_KHOE("Sức Khỏe"),
    KHOA_HOC("Khoa học"),
    GIAO_DUC("Giáo Dục"),
    KINH_DOANH("Kinh doanh"),
    DOI_SONG("Đời sống");

    private final String name;

    CategoryName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
