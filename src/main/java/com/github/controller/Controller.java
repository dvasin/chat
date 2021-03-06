package com.github.controller;

import com.github.controller.command.Command;
import com.github.controller.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chat")
public class Controller extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(Controller.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = new CommandFactory().defineCommand(req);
        logger.info("Command - {}", command.toString());
        String page = command.execute(req);

        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }

}
