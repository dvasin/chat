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
    }, REFRESH {
        {
            this.command = new RefreshCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }

}
