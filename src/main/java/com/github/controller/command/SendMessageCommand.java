package com.github.controller.command;

import com.github.Message;
import com.github.User;
import com.github.dao.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SendMessageCommand implements Command {
    private static Logger logger = LoggerFactory.getLogger(SendMessageCommand.class);


    public String execute(HttpServletRequest request) {
        DaoFactory factory = DaoFactory.getInstance();
        User user = (User)request.getSession().getAttribute("user");
        logger.info(user.toString());
        String text = request.getParameter("message");
        factory.getMessageDAO().addMessage(new Message(user, new Date(), text));
        return "/jsp/chat.jsp";
    }
}
