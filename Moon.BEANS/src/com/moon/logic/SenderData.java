package com.moon.logic;


public class SenderData {

    public static String login;
    public static String password;
    public static String to;
    public static String subject;
    public static String content;


    public static String getContent() {
        return content;
    }

    public static void setContent(String content) {
        SenderData.content = content;
    }

    public static String getSubject() {

        return subject;
    }

    public static void setSubject(String subject) {
        SenderData.subject = subject;
    }

    public static String getTo() {

        return to;
    }

    public static void setTo(String to) {
        SenderData.to = to;
    }

    public static String getPassword() {

        return password;
    }

    public static void setPassword(String password) {
        SenderData.password = password;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        SenderData.login = login;
    }
}
