package com.experess.news.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Bucket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseService {


    public String uploadMedia(MultipartFile file) throws IOException {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        Bucket bucket = StorageClient.getInstance(firebaseApp).bucket();

        String fileName = file.getOriginalFilename();
        InputStream fileInputStream = file.getInputStream();
        return bucket.create(fileName, fileInputStream, file
                        .getContentType())
                .getMediaLink();
    }


}
