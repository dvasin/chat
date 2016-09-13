package com.github.dao;

import com.github.dao.mysql.SqlDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory factory;

    public static DaoFactory getInstance() {
        if(factory == null) {
                factory = new SqlDaoFactory();
                return factory;
        } else return factory;
    }

    public abstract MessageDao getMessageDAO();
    public abstract UserDao getUserDAO();
}
