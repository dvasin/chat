package com.github.sql;

import java.sql.*;
import java.util.Map;
import static com.github.sql.ConnectionPool.*;

public class WrapperExecuteQuery {

    public ResultSet executeSimpleQuery(String sql_query) {
        Connection c = getConnection();
        ResultSet resultSet = null;
        Statement st;
        try {
            st = c.createStatement();
            resultSet = st.executeQuery(sql_query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(c != null) {
                freeConnection(c);
            }
        }

        return resultSet;
    }

    public ResultSet executeParametrizedQuery(String sql_query, Map<Integer, Object> parametersMap) {
        Connection c = getConnection();
        ResultSet resultSet = null;
        PreparedStatement pst;
        try {
            pst = c.prepareStatement(sql_query);

            for (Map.Entry<Integer, Object> entry : parametersMap.entrySet()) {
                if (entry.getValue() instanceof Integer) {
                    pst.setInt(entry.getKey(), (Integer) entry.getValue());
                } else if (entry.getValue() instanceof Date) {
                    pst.setDate(entry.getKey(), (Date) entry.getValue());
                } else if (entry.getValue() instanceof String) {
                    pst.setString(entry.getKey(), String.valueOf(entry.getValue()));
                } else {
                    throw new Exception("Parameter value is not valid");
                }
            }
            resultSet = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(c != null) {
                freeConnection(c);
            }
        }

        return resultSet;
    }

    public void executeQueryCallableStatement(String sql_query, Map<Integer, Object> parametersMap) {
        Connection c = getConnection();
        try {
            CallableStatement cst = c.prepareCall(sql_query);
            for (Map.Entry<Integer, Object> entry : parametersMap.entrySet()) {
                if (entry.getValue() instanceof Integer) {
                    cst.setInt(entry.getKey(), (Integer) entry.getValue());
                } else if (entry.getValue() instanceof Date) {
                    cst.setDate(entry.getKey(), (Date) entry.getValue());
                } else if (entry.getValue() instanceof String) {
                    cst.setString(entry.getKey(), entry.getValue().toString());
                } else {
                    throw new Exception("Parameter value is not valid");
                }
            }
            cst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(c != null) {
                freeConnection(c);
            }
        }
    }
}
