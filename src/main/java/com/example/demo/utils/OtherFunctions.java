package com.example.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OtherFunctions {
    public static byte[] UploadImg(String url) throws Exception {
        URL imageUrl = new URL(url);
        try (InputStream inputStream = imageUrl.openStream();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, bytesRead, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static String DateSystem(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
