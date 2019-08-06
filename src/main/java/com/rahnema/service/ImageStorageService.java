package com.rahnema.service;

import com.rahnema.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageStorageService implements StorageService {

    private final Path auctionPath;
    private final Path avatarPath;

    @Autowired
    public ImageStorageService(StorageProperties storageProperties) {
        this.avatarPath = Paths.get(storageProperties.getAvatarsLocation());
        this.auctionPath = Paths.get(storageProperties.getBannersLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(auctionPath);
            Files.createDirectories(avatarPath);
        } catch (IOException e) {
            throw new RuntimeException("could not initialize");
        }
    }

    @Override
    public void storeAvatar(MultipartFile file) {
        store(file, avatarPath);
    }

    @Override
    public void storeBanner(MultipartFile file) {
        store(file, auctionPath);
    }

    @Override
    public Path loadAvatar(String filename) {
        return load(filename, avatarPath);
    }

    @Override
    public Path loadBanner(String filename) {
        return load(filename, auctionPath);
    }

    @Override
    public Resource loadBannerAsResource(String filename) {
        return loadAsResource(filename, auctionPath);
    }

    @Override
    public Resource loadAvatarAsResource(String filename) {
        return loadAsResource(filename, avatarPath);
    }

    private void store(MultipartFile file, Path path) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty())
                throw new IllegalArgumentException("failed to store empty file" + filename);
            if (filename.contains(".."))
                throw new IllegalArgumentException("relative path error" + filename);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            throw new RuntimeException("store failed ");
        }
    }

    private Path load(String filename, Path path) {
        return path.resolve(filename);
    }

    private Resource loadAsResource(String filename, Path path) {
        try {
            Path file = load(filename, path);
            return new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("could not read file" + filename);
        }
    }
}
