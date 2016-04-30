package com.moon.creators;


import com.moon.GUI.FileChooser;
import com.moon.GUI.ProgressFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCreator implements Runnable {

    public static List<String> dataList = new ArrayList<>();
    private File to;
    private String currLine = null;

    public DatabaseCreator() {
        createTxt();
        parse(this.to);
    }

    private void createTxt() {
        to = new File(FirstLaunchDataCreator.filePath + "/database.txt");
    }

    private void parse(File to) {
        try {

            dataList = new ArrayList<>();
            FileReader fromReader = new FileReader(FileChooser.DATA_PATH);
            BufferedReader bfrd = new BufferedReader(fromReader);
            ProgressFrame pf = new ProgressFrame();
            pf.on();

            while ((currLine = bfrd.readLine()) != null) {
                parseEmails(currLine);
            }
            writeToFile(dataList);
            pf.off();

        } catch (IOException e) {}
    }


    private void parseEmails(String currLine) {

        int prevPos = 0, nextPos;
        final String SEARCH_STR = "@gmail.com";
        final int SEARCH_STR_LENGTH = SEARCH_STR.length();

        while (true) {
            nextPos = currLine.indexOf(SEARCH_STR, prevPos);
            if (nextPos == -1)
                break;

            dataList.add(currLine.substring(prevPos, nextPos + SEARCH_STR_LENGTH));
            prevPos = nextPos + SEARCH_STR_LENGTH;
        }

    }

    private void writeToFile(List<String> dataList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(to));

            for (int i = 0; i < dataList.size(); i++) {
                writer.write(dataList.get(i));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ошибка в копировании файла");
        }
    }

    @Override
    public void run() {
        new DatabaseCreator();
    }

}
