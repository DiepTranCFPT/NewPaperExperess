package com.example.demo.utils;

import com.example.demo.entity.Rating;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class OtherFunctions {
    public static byte[] UploadImg(String fileName) throws Exception {
        try (InputStream inputStream = Objects.requireNonNull(OtherFunctions.class.getClassLoader().getResourceAsStream(fileName), "File not found: " + fileName)) {
            return inputStream.readAllBytes();
        }
    }

    public static String DateSystem() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String generateRandomNumberString() {
        return String.format("%06d", new Random().nextInt(100000));
    }

    public static int rating(List<Rating> articles){
        int rating = 0;
        for (Rating r : articles){
            rating += r.getRating();
        }
        return rating / articles.size();
    }

}
