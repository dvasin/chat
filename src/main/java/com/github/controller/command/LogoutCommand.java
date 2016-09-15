package com.github.controller.command;

import com.github.User;
import com.github.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    public String execute(HttpServletRequest request) {
        DaoFactory factory = DaoFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        factory.getUserDAO().logout(user);

        session.invalidate();
        return "/jsp/index.jsp";
    }
}
