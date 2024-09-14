package com.example.demo.utils;

import com.example.demo.entity.Rating;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class OtherFunctions {
    public static byte[] UploadImg(String fileName) throws Exception {
        try (InputStream inputStream = Objects.requireNonNull(OtherFunctions.class.getClassLoader()
                .getResourceAsStream(fileName), "File not found: " + fileName)) {
            return inputStream.readAllBytes();
        }
    }

    public static BufferedImage convertByteArrayToImage(byte[] imageData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            return ImageIO.read(bais);
        }
    }

    public static String DateSystem() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String generateRandomNumberString() {
        return String.format("%06d", new Random().nextInt(100000));
    }

    public static int ratingMedium(List<Rating> articles) {
        return (articles == null || articles.isEmpty()) ? 0 : articles.stream()
                        .mapToInt(Rating::getRating).sum() / articles.size();
    }

}
