package com.github.controller.command;

public enum EnumCommand {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SENDMESSAGE {
        {
            this.command = new SendMessageCommand();
        }
    }, GETLASTMESSAGES {
        {
            this.command = new GetLastMessages();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }

}
