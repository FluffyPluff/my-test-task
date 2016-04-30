package com.moon.GUI;

import javax.swing.JFrame;


public class ProgressFrame extends JFrame {

    private javax.swing.JProgressBar copyBar;

    public ProgressFrame() {
        setBounds(300, 300, 400, 400);
        initComponents();
        setVisible(true);
    }


    private void initComponents() {



            copyBar = new javax.swing.JProgressBar();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setTitle("Копирование..");

            copyBar.setForeground(new java.awt.Color(255, 204, 204));

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(copyBar, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(copyBar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(31, Short.MAX_VALUE))
            );


            pack();
        }

        public void on() {
            copyBar.setIndeterminate(true);
        }

        public void off() {
            dispose();
        }

}
