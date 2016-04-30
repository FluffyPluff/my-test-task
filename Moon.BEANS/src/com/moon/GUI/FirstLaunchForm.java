package com.moon.GUI;


import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstLaunchForm extends JFrame {


    private javax.swing.JTextArea FirstLaunchInformation;
    private javax.swing.JButton continueBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton nextBtn;


    public FirstLaunchForm() {
        setBounds(300, 300, 400, 400);
        initComponents();
        initListeners();
        setVisible(true);
    }


    private void initListeners() {

        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FileChooser();
            }
        });


    }





    private void initComponents() {


        jScrollPane1 = new javax.swing.JScrollPane();
        FirstLaunchInformation = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        nextBtn = new javax.swing.JButton();
        continueBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FirstLaunchInformation.setEditable(false);
        FirstLaunchInformation.setColumns(1);
        FirstLaunchInformation.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        FirstLaunchInformation.setLineWrap(true);
        FirstLaunchInformation.setRows(5);
        FirstLaunchInformation.setText("Пожалуйста, укажите текстовый файл с БД пользователей для рассылки >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        jScrollPane1.setViewportView(FirstLaunchInformation);

        nextBtn.setBackground(new java.awt.Color(255, 204, 255));
        nextBtn.setForeground(new java.awt.Color(255, 255, 255));
        nextBtn.setText("Указать >");

        continueBtn.setBackground(new java.awt.Color(255, 204, 255));
        continueBtn.setForeground(new java.awt.Color(255, 255, 255));
        continueBtn.setText("Пропустить");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nextBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(continueBtn)
                                                .addGap(0, 172, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nextBtn)
                                        .addComponent(continueBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();

    }


}
