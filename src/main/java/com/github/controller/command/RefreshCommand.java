package com.github.controller.command;

import com.github.Message;
import com.github.User;
import com.github.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RefreshCommand implements Command {

    private static final int COUNT_LAST_MESSAGES = 15;

    public String execute(HttpServletRequest request) {
        DaoFactory factory = DaoFactory.getInstance();
        List<Message> messages = factory.getMessageDAO().getLastMessages(COUNT_LAST_MESSAGES);
        List<User> users = factory.getUserDAO().getLoggedinUsers();
        HttpSession session = request.getSession();
        session.setAttribute("messages",messages);
        session.setAttribute("users",users);
        return "/jsp/chat.jsp";
    }

    @Override
    public String toString() {
        return "RefreshCommand";
    }
}
