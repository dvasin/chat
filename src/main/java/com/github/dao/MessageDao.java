package com.github.dao;

import com.github.Message;

import java.util.List;

public interface MessageDao {

    void addMessage(Message message);
    List<Message> getLastMessages(int amount);
}
