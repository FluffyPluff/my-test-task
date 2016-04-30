package com.moon.creators;


import com.moon.GUI.FirstLaunchForm;

import java.io.File;

public class FirstLaunchDataCreator {

    private File folder;

    public static final String filePath = System.getProperty("user.home") + "/Moon.BEANS";

    public FirstLaunchDataCreator() {
        createFile();
    }

    private void createFile() {
        folder = new File(filePath);
        if (!folder.mkdirs()) {
            new FirstLaunchForm();
            folder.mkdir();
        }
    }



}
