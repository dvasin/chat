package com.github.dao;

import com.github.dao.mysql.SqlDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory factory;

    public static DaoFactory getInstance(Factory factoryLocal) {
        if(factory == null) {
            if(factoryLocal.equals(Factory.MYSQL))
            factory = new SqlDaoFactory();
            return factory;
        } else return factory;
    }

    public abstract MessageDao getMessageDAO();
    public abstract UserDao getUserDAO();
}
