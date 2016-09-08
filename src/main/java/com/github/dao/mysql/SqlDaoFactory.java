package com.github.dao.mysql;

import com.github.dao.DaoFactory;
import com.github.dao.MessageDao;
import com.github.dao.UserDao;

public class SqlDaoFactory extends DaoFactory {

    public MessageDao getMessageDAO() {
        return null;
    }

    public UserDao getUserDAO() {
        return null;
    }
}
