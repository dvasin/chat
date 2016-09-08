package com.github.dao.mysql;

import com.github.Message;
import com.github.Role;
import com.github.Status;
import com.github.User;
import com.github.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUserDao implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(SqlUserDao.class);
    private static final String LOGIN_EXISTS_USER = "UPDATE User SET Status = 'loggedin' WHERE NICK = ?";
    private static final String LOGOUT_USER = "UPDATE User SET Status = 'loggedout' WHERE NICK = ?";
    private static final String KICK_USER = "UPDATE User SET Status = 'kicked' WHERE NICK = ?";
    private static final String GET_ALL_USERS = "SELECT NICK FROM User";
    private static final String GET_ALL_LOGGEDIN_USERS = "SELECT * FROM User WHERE status = 'loggedin'";
    private static final String GET_USER_STATUS = "SELECT status from User where nick = ?";
    private static final String GET_USER_ROLE = "SELECT role from User where nick = ?";
    private static final String CREATE_NEW_USER = "INSERT INTO User VALUES(?, ?, ?)";

    public void login(User user) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (getStatus(user).name().equals(Status.NOT_EXIST.name())) {
            map.put(1, user.getNickname());
            map.put(2, Status.LOGGEDIN.name().toLowerCase());
            map.put(3, user.getRole().name().toLowerCase());
            try {
                new WrapperExecuteUpdate().executeParametrizedUpdate(CREATE_NEW_USER, map);
            } catch (SQLException e) {
                logger.error("Неуспешная попытка создания нового пользователя");
            }
        } else {
            map.put(1, user.getNickname());
            try {
                new WrapperExecuteUpdate().executeParametrizedUpdate(LOGIN_EXISTS_USER, map);
            } catch (SQLException e) {
                logger.error("Неуспешная попытка обновления статуса (loggedout) пользователя {}", user);
            }
        }
    }

    public void logout(User user) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, user.getNickname());
        try {
            new WrapperExecuteUpdate().executeParametrizedUpdate(LOGOUT_USER, map);
        } catch (SQLException e) {
            logger.error("Неуспешная попытка обновления статуса (loggedout) пользователя {}", user);
        }
    }

    public void kick(User user) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, user.getNickname());
        try {
            new WrapperExecuteUpdate().executeParametrizedUpdate(LOGOUT_USER, map);
        } catch (SQLException e) {
            logger.error("Неуспешная попытка обновления статуса (kicked) пользователя {}", user);
        }
    }

    public void kick(User whoKicked, User thatKicked) {
        kick(thatKicked);
        new SqlMessageDao().addMessage(new Message(whoKicked, new Date(), String.format("Был выкинут пользователь %s", thatKicked.getNickname())));
    }

    public Status getStatus(User user) {
        try {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, user.getNickname());
            ResultSet rs = new WrapperExecuteQuery().executeParametrizedQuery(GET_USER_STATUS, map);
            if (rs.next()) {
                return Status.valueOf(rs.getString(1).toUpperCase());
            }
        } catch (SQLException e) {
            logger.error("Неуспешное получение статуса:  {}", e.getMessage());
        }
        return Status.NOT_EXIST;
    }

    public Role getRole(User user) {
        try {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, user.getNickname());
            ResultSet rs = new WrapperExecuteQuery().executeParametrizedQuery(GET_USER_ROLE, map);
            if (rs.next()) {
                return Role.valueOf(rs.getString(1).toUpperCase());
            }
        } catch (SQLException e) {
            logger.error("Неуспешное получение статуса:  {}", e.getMessage());
        }
        return Role.NOT_EXIST;
    }

    public void unkick(User user) {

    }

    public List<User> getLoggedinUsers() {
        return null;
    }
}
