package com.github.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    public Command defineCommand(HttpServletRequest request) {
        Command command = new EmptyCommand();
        String commandFromParameter = request.getParameter("command");
        if (commandFromParameter == null || commandFromParameter.equals("")) {
            return command;
        }
        command = EnumCommand.valueOf(commandFromParameter.toUpperCase()).getCommand();
        return command;

    }
}
