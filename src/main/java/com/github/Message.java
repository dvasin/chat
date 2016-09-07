package com.github;

import java.util.Date;

public class Message {

    private User user;
    private Date date;
    private String textMessage;

    public Message(User user, Date date, String textMessage) {
        this.user = user;
        this.date = date;
        this.textMessage = textMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", date=" + date +
                ", textMessage='" + textMessage + '\'' +
                '}';
    }
}
