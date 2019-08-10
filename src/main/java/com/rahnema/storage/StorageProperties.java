package com.rahnema.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    @Value("${storage.upload.folder}")
    private String location;

    public String getAvatarsLocation() {
        return location + "/avatars";

    }

    public String getBannersLocation() {
        return location + "/banners";
    }

}