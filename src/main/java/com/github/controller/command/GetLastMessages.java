package com.github.controller.command;

import com.github.Message;
import com.github.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetLastMessages implements Command {

    private static final int COUNT_LAST_MESSAGES = 15;

    public String execute(HttpServletRequest request) {
        DaoFactory factory = DaoFactory.getInstance();
        List<Message> messages = factory.getMessageDAO().getLastMessages(COUNT_LAST_MESSAGES);
        HttpSession session = request.getSession();
        session.setAttribute("messages",messages);
        return "/jsp/chat.jsp";
    }
}