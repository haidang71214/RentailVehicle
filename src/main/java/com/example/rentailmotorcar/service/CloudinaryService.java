package com.example.rentailmotorcar.service;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CloudinaryService {
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));
        return uploadResult.get("secure_url").toString();
    }
    public String deleteFile(String publicId) throws IOException {
        Map result = cloudinary.uploader().destroy(publicId,
                ObjectUtils.asMap("resource_type", "image")); 
        return result.get("result").toString();
    }
}
