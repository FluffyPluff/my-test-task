package com.moon.creators;


import java.io.File;

public class FileCreator  {

    private File folder;

    public static final String filePath = System.getProperty("user.home") + "/Moon.BEANS";

    public FileCreator() {
        createFile();
    }

    private void createFile() {
        folder = new File(filePath);
        if (!folder.mkdirs()) {
            folder.mkdir();
        }
    }
}


