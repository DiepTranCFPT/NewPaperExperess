package com.experess.news.utils;

import com.experess.news.entity.Rating;

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
import java.util.function.Function;

public class OtherFunctions {


    /**
     * chuyen filename thanh byte[]
     *
     * @param fileName {@link String}
     * @return {@link byte[]}
     */
    public static byte[] UploadImg(String fileName) throws Exception {
        try (InputStream inputStream = Objects.requireNonNull(OtherFunctions.class.getClassLoader()
                .getResourceAsStream(fileName), "File not found: " + fileName)) {
            return inputStream.readAllBytes();
        }
    }


    /**
     * @param imageData {@link byte[]}
     * @return {@link BufferedImage}
     * @throws IOException log erro with IOException
     */
    public static BufferedImage convertByteArrayToImage(byte[] imageData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            return ImageIO.read(bais);
        }
    }


    /**
     * lay ngay gio he thonng va chuyen thanh {@link String}
     *
     * @return {@link String} ngay he thong voi format dd/MM/yyyy
     */

    public static String DateSystem() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    /**
     * random 1 ma code voi 6 so tu 000001 - 999999
     *
     * @return {@link String} code random
     */
    public static String generateRandomNumberString() {
        return String.format("%06d", new Random().nextInt(100000));
    }

    /**
     * tinh trung binh cong danh gia trong 1 bai viet
     *
     * @param articles {@link List<Rating>}
     * @return {@link int}  0 - 5
     */
    public static int ratingMedium(List<Rating> articles) {
        return (articles == null || articles.isEmpty()) ? 0 : articles.stream()
                .mapToInt(Rating::getRating).sum() / articles.size();

    }

    /**
     * chuyen danh sach thanh danh sach String id
     *
     * @param list   {@link List<T>}
     * @param method {@link Function<T, String>}
     * @param <T>    {@link Object}
     */


    public static <T> List<String> getListStringID(List<T> list,
                                                   Function<T, String> method) {
        return List.copyOf(list.stream().map(method).toList());
    }

    public static <T, M> List<M> getListObject(List<T> list,
                                               Function<T, M> method) {
        return list.stream()
                .map(method)
                .toList();
    }

}
