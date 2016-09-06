package com.github.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Map;

import static com.github.sql.ConnectionPool.freeConnection;
import static com.github.sql.ConnectionPool.getConnection;

public class WrapperExecuteUpdate {

    public int executeParametrizedUpdate(String sql_query, Map<Integer, Object> parametersMap) {
        int rowsInsert;
        Connection c = getConnection();
        try {
            PreparedStatement ps = c.prepareStatement(sql_query);
            for (Map.Entry<Integer, Object> entry : parametersMap.entrySet()) {
                if (entry.getValue() instanceof Integer) {
                    ps.setInt(entry.getKey(), (Integer) entry.getValue());
                } else if (entry.getValue() instanceof Date) {
                    ps.setDate(entry.getKey(), (Date) entry.getValue());
                } else if (entry.getValue() instanceof String) {
                    ps.setString(entry.getKey(), entry.getValue().toString());
                } else {
                    throw new Exception("Parameter value is not valid");
                }
            }
            rowsInsert = ps.executeUpdate();
            return rowsInsert;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(c != null) {
                freeConnection(c);
            }
        }
        return 0;
    }
}
