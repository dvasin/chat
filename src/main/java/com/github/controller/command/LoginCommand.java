package com.github.controller.command;

import com.github.Role;
import com.github.Status;
import com.github.User;
import com.github.dao.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static Logger logger = LoggerFactory.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request) {
        DaoFactory factory = DaoFactory.getInstance();
        String nickName = request.getParameter("nickName");
        Status status = factory.getUserDAO().getStatus(nickName);
        Role role = factory.getUserDAO().getRole(nickName);

        User user = new User(nickName, status, role);
        factory.getUserDAO().login(user);
        return "/jsp/index.jsp";
    }
}
