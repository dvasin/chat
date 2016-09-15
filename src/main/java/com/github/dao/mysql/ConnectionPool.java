package com.github.dao.mysql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static final String USER = "denis";
    private static final String PASSWORD = "password";
    private static final String URL = "jdbc:mysql://109.94.176.197:3306/dvasin";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static ConnectionPool getInstance() {
        if (connectionPool == null) {
            Driver driver = null;
            try {
                driver = (Driver) Class.forName(
                        DRIVER_NAME).newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e){
                e.printStackTrace();
            }
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
