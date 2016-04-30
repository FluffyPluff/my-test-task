package com.moon.GUI;


import com.moon.creators.DatabaseCreator;

import javax.swing.*;
import java.io.File;

public class FileChooser extends JFrame {

    private javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();;
    public static String DATA_PATH = "";


    public FileChooser() {
        initComponents();
        OpenActionPerformed();
    }

    private void initComponents() {

        setBounds(300, 300, 500, 500);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(fileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();


    }

    private void OpenActionPerformed() {

        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            DATA_PATH = file.getAbsolutePath();
            dispose();
        }

        new DatabaseCreator();


    }
}
