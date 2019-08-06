package com.rahnema.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = System.getProperty("user.dir") + "/upload-dir";
    private String avatarsLocation = location + "/avatars";
    private String bannersLocation = location + "/banners";


    public String getAvatarsLocation() {
        return avatarsLocation;
    }

    public String getBannersLocation() {
        return bannersLocation;
    }


}