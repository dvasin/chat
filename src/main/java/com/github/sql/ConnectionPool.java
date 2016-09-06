package com.github.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static final String USER = "denis";
    private static final String PASSWORD = "password";
    private static final String URL = "jdbc:mysql://109.94.176.197:3306/dvasin";
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static ConnectionPool getInstance() {
        if (connectionPool == null) {
            return new ConnectionPool();
        } else {
            return connectionPool;
        }
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void freeConnection(Connection c) {

    }

}
