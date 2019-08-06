package com.rahnema.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;


public interface StorageService {

    void init();

    void storeAvatar(MultipartFile file);

    void storeBanner(MultipartFile file);

    Path loadAvatar(String filename);

    Path loadBanner(String filename);

    Resource loadAvatarAsResource(String filename);

    Resource loadBannerAsResource(String filename);


}

