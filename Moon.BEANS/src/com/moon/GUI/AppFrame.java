package com.moon.GUI;

import com.moon.creators.DatabaseCreator;
import com.moon.creators.FirstLaunchDataCreator;
import com.moon.logic.Sender;
import com.moon.logic.SenderData;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;


public class AppFrame extends JFrame implements Runnable {


    private javax.swing.JCheckBox HTMLCheckbox;
    private javax.swing.JMenuItem about;
    private javax.swing.JCheckBox deliveryCheckbox;
    private javax.swing.JMenuItem help;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField subjectField;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTextPane userInputField;
    private javax.swing.JTextField usernameField;



    public AppFrame() {
        super("Moon.BEANS>");
        setLocation(300, 300);
        setFrameIcon();
        initComponents();
        initListeners();

    }


    private void setFrameIcon() {

    }

    @SuppressWarnings("unchecked")
    private void initComponents() {



            sendButton = new javax.swing.JButton();
            jSeparator1 = new javax.swing.JSeparator();
            jScrollPane1 = new javax.swing.JScrollPane();
            userInputField = new javax.swing.JTextPane();
            deliveryCheckbox = new javax.swing.JCheckBox();
            jSeparator2 = new javax.swing.JSeparator();
            usernameField = new javax.swing.JTextField();
            toLabel = new javax.swing.JLabel();
            HTMLCheckbox = new javax.swing.JCheckBox();
            subjectLabel = new javax.swing.JLabel();
            subjectField = new javax.swing.JTextField();
            jMenuBar1 = new javax.swing.JMenuBar();
            helpMenu = new javax.swing.JMenu();
            help = new javax.swing.JMenuItem();
            about = new javax.swing.JMenuItem();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            sendButton.setBackground(new java.awt.Color(255, 204, 255));
            sendButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
            sendButton.setForeground(new java.awt.Color(255, 255, 255));
            sendButton.setText("Отправить");

            userInputField.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
            jScrollPane1.setViewportView(userInputField);

            deliveryCheckbox.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
            deliveryCheckbox.setText("рассылка");

            usernameField.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

            toLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
            toLabel.setText("кому");

            HTMLCheckbox.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
            HTMLCheckbox.setText("HTML формат");

            subjectLabel.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
            subjectLabel.setText("тема");

            subjectField.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

            helpMenu.setText("Помощь");

            help.setText("Руководство");
            helpMenu.add(help);

            about.setText("О приложении");
            helpMenu.add(about);

            jMenuBar1.add(helpMenu);

            setJMenuBar(jMenuBar1);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane1)
                                            .addComponent(jSeparator1)
                                            .addComponent(jSeparator2)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(toLabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(usernameField))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(sendButton)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(deliveryCheckbox)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(HTMLCheckbox)
                                                    .addGap(0, 52, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(subjectLabel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(subjectField)))
                                    .addContainerGap())
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(toLabel))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(subjectLabel)
                                            .addComponent(subjectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(sendButton)
                                            .addComponent(deliveryCheckbox)
                                            .addComponent(HTMLCheckbox))
                                    .addContainerGap())
            );

            pack();
        }

    private void initListeners() {

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SenderData.setSubject(subjectField.getText());
                SenderData.setContent(userInputField.getText());

                if (!deliveryCheckbox.isSelected()) {
                    SenderData.setTo(usernameField.getText());
                    Sender sender = new Sender(new SenderData());
                    try {
                        sender.sendMessage();
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                } else {

                    File _database = new File(FirstLaunchDataCreator.filePath + "/database.txt");

                    if (!_database.exists()) {
//                        DatabaseCreator dc = new DatabaseCreator();
//                        Thread dcThread = new Thread(dc);
//                        dcThread.start();
//
//                        try {
//                            dcThread.join();
//                        } catch (InterruptedException ex) {}
                        new FirstLaunchForm();

                    } else {

                     for (String emails : DatabaseCreator.dataList) {
                        SenderData.setTo(emails);
                         Sender sender = new Sender(new SenderData());
                         Thread senderThread = new Thread(new Runnable() {
                             @Override
                             public void run() {
                                try {
                                    sender.sendMessage();
                                } catch (MessagingException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedEncodingException e1) {
                                    e1.printStackTrace();
                                }
                             }
                         });
                         senderThread.start();

                     }
                    }
                }
            }
        });




    }

    @Override
    public void run() {
     new AppFrame().setVisible(true);
   }

}
