package com.github.dao.mysql;

import com.github.dao.DaoFactory;
import com.github.dao.MessageDao;
import com.github.dao.UserDao;

public class SqlDaoFactory extends DaoFactory {

    public MessageDao getMessageDAO() {
        return new SqlMessageDao();
    }

    public UserDao getUserDAO() {
        return new SqlUserDao();
    }
}
