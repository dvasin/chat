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
import java.util.*;

public class SqlUserDao implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(SqlUserDao.class);
    private static final String LOGIN_EXISTS_USER = "UPDATE user SET Status = 'loggedin' WHERE NICK = ?";
    private static final String LOGOUT_USER = "UPDATE user SET Status = 'loggedout' WHERE NICK = ?";
    private static final String KICK_USER = "UPDATE user SET Status = 'kicked' WHERE NICK = ?";
    private static final String GET_USER_STATUS = "SELECT status from user where nick = ?";
    private static final String GET_USER_ROLE = "SELECT role from user where nick = ?";
    private static final String CREATE_NEW_USER = "INSERT INTO user VALUES(?, ?, ?)";
    private static final String GET_ONLINE_USERS = "SELECT nick FROM user WHERE status='loggedin'";

    public void login(User user) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (getStatus(user.getNickname()).equals(Status.NOT_EXIST)) {
            map.put(1, user.getNickname());
            map.put(2, Status.LOGGEDIN.name().toLowerCase());
            map.put(3, Role.USER.name().toLowerCase());
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
            new WrapperExecuteUpdate().executeParametrizedUpdate(KICK_USER, map);
        } catch (SQLException e) {
            logger.error("Неуспешная попытка обновления статуса (kicked) пользователя {}", user);
        }
    }

    public void kick(User whoKicked, User thatKicked) {
        kick(thatKicked);
        new SqlMessageDao().addMessage(new Message(whoKicked, new Date(), String.format("Был выкинут пользователь %s", thatKicked.getNickname())));
    }

    public Status getStatus(String nickName) {
        try {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, nickName);
            ResultSet rs = new WrapperExecuteQuery().executeParametrizedQuery(GET_USER_STATUS, map);
            if (rs.next()) {
                return Status.valueOf(rs.getString(1).toUpperCase());
            }
        } catch (SQLException e) {
            logger.error("Неуспешное получение статуса:  {}", e.getMessage());
        }
        return Status.NOT_EXIST;
    }

    public Role getRole(String nickName) {
        try {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, nickName);
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
        List<User> users = new ArrayList<User>();
        try {
            ResultSet rs = new WrapperExecuteQuery().executeSimpleQuery(GET_ONLINE_USERS);
            while (rs.next()) {
                users.add(new User(rs.getString(1)));
            }
        } catch (SQLException e) {
            logger.error("Неуспешное получение пользователей онлайн:  {}", e.getMessage());
        }
        return users;
    }
}
