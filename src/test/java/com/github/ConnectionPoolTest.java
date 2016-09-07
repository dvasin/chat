package com.github;

import com.github.dao.mysql.WrapperExecuteQuery;
import com.github.dao.mysql.WrapperExecuteUpdate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ConnectionPoolTest {
    private static Logger logger = LoggerFactory.getLogger(ConnectionPoolTest.class);
    @Test
    public void testSelect() throws Exception {
        logger.info("-----------------------------------------------");
        logger.info("testSelect");
        logger.info("-----------------------------------------------");
        String query = "select * from User";
        // executing SELECT query
        ResultSet rs = new WrapperExecuteQuery().executeSimpleQuery(query);
        //stmt.executeUpdate(query);
        int counter = 0;
        while (rs.next()) {
            counter++;
            logger.info("{} | {} | {} | ",rs.getString(1),rs.getString(2),rs.getString(3));
        }
        Assert.assertTrue(counter > 0);
        logger.info("-----------------------------------------------");
    }

    @Test
    public void testInsert() throws Exception {
        logger.info("-----------------------------------------------");
        logger.info("testInsert");
        logger.info("-----------------------------------------------");
        String query = "INSERT INTO User VALUES(?, ?, ?)";
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, "nick"+new Random().nextInt(10000));
        map.put(2, "loggedin");
        map.put(3, "user");
        // executing SELECT query
        int countRows = new WrapperExecuteUpdate().executeParametrizedUpdate(query, map);
        logger.info("insert rows = {}", countRows);
        Assert.assertTrue(countRows > 0);
    }


}