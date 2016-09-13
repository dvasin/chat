package com.github.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chat")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* processRequest(req, resp);*/
        resp.setContentType("text/html");
        resp.getWriter().print("This is " + this.getClass().getName() + "do GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*processRequest(req, resp);*/
        resp.setContentType("text/html");
        resp.getWriter().print("This is " + this.getClass().getName() + "do POST");
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(new LoginCommand().execute(req));
        dispatcher.forward(req, resp);*/
    }

}
