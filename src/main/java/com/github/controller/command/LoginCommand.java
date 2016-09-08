package com.github.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static Logger logger = LoggerFactory.getLogger(LoginCommand.class);

    public String execute(HttpServletRequest request) {
        return null;
    }
}
