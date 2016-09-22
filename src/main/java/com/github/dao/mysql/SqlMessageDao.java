package com.github.dao.mysql;

import com.github.Message;
import com.github.User;
import com.github.dao.MessageDao;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlMessageDao implements MessageDao {
    private static Logger logger = LoggerFactory.getLogger(SqlMessageDao.class);
    private static final String ADD_MESSAGE = "INSERT INTO Message(nick, date, text) VALUES(?,?, ?)";
    private static final String GET_LAST_MESSAGES = "SELECT * FROM Message WHERE id > (SELECT MAX(id) - ? FROM Message) ORDER BY id DESC";

    public void addMessage(Message message) {
        User user = message.getUser();
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, user.getNickname());
        map.put(2, String.format("%tY-%<tm-%<td %<tH:%<tM:%<tS", message.getDate()));
        map.put(3, message.getTextMessage());
        try {
            new WrapperExecuteUpdate().executeParametrizedUpdate(ADD_MESSAGE, map);
        } catch (SQLException e) {
            logger.error("Error method addMessage : {}", e.getMessage());
        }
    }

    public List<Message> getLastMessages(int amount) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, amount);
        List<Message> messages = new ArrayList<Message>(amount);
        try {
            ResultSet rs = new WrapperExecuteQuery().executeParametrizedQuery(GET_LAST_MESSAGES, map);
            while (rs.next()) {
                String nickName = rs.getString(2);
                User user = new User(nickName);
                messages.add(new Message(user, new Date(rs.getTimestamp(3).getTime()), rs.getString(4)));
            }
        } catch (SQLException e) {
            logger.error("Error method getLastMessages {}", e.getMessage());
        }
        return Lists.reverse(messages);
    }
}
