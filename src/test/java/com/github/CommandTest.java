package com.github;

import com.github.dao.DaoFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandTest {
    private static Logger logger = LoggerFactory.getLogger(CommandTest.class);

    @Test
    public void loginTest() {

        DaoFactory factory = DaoFactory.getInstance();
        String nickName = "test1";
        logger.info(nickName);
        Status status = factory.getUserDAO().getStatus(nickName);
        Role role = factory.getUserDAO().getRole(nickName);

        User user = new User(nickName, status, role);
        factory.getUserDAO().login(user);

    }
    @Test
    public void logoutTest() {
        DaoFactory factory = DaoFactory.getInstance();
        String nickName = "test1";
        logger.info(nickName);
        Status status = factory.getUserDAO().getStatus(nickName);
        Role role = factory.getUserDAO().getRole(nickName);

        User user = new User(nickName, status, role);
        factory.getUserDAO().logout(user);

        status = factory.getUserDAO().getStatus(nickName);
        role = factory.getUserDAO().getRole(nickName);

        logger.info(String.valueOf(status));
        logger.info(String.valueOf(role));
    }

}
