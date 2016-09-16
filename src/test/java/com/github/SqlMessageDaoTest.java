package com.github;

import com.github.dao.mysql.SqlMessageDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;

public class SqlMessageDaoTest {
    private static Logger logger = LoggerFactory.getLogger(SqlMessageDaoTest.class);
    @Test
    public void addMessageTest() {
        new SqlMessageDao().addMessage(new Message(new User("test", Status.LOGGEDIN, Role.USER), new Date(), "test text" + new Random().nextInt(99)));
    }

    @Test
    public void getLastMessagesTest() {
        for(Message m : new SqlMessageDao().getLastMessages(25)) {
            logger.info(m.toString());
        }
    }

/*    @Test
    public void getDateFromDB() throws Exception {
        ResultSet rs = new WrapperExecuteQuery().executeSimpleQuery("select * from Message");
        rs.next();
        logger.info(new Date(rs.getTimestamp(3).getTime()).toString());
    }*/

}
