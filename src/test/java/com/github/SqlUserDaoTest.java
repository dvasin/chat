package com.github;

import com.github.dao.mysql.SqlUserDao;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SqlUserDaoTest {
    private static Logger logger = LoggerFactory.getLogger(SqlUserDaoTest.class);

    @Test
    public void getStatusNotExistUser() {
        Status status =  new SqlUserDao().getStatus("not exist");
        Assert.assertEquals("Проверка статусов", status.name(), Status.NOT_EXIST.name());
        logger.info(status.toString());
    }

    @Test
    public void getStatusLoggedinUser() {
        Status status =  new SqlUserDao().getStatus("test");
        Assert.assertEquals("Проверка статусов", status.name(), Status.LOGGEDIN.name());
        logger.info(status.toString());
    }

    @Test
    public void loginTest() {
        String nickName = "random" + new Random().nextInt(1000);
        Status status =  Status.NOT_EXIST;
        Role role = Role.USER;
        logger.info("nickName = {}",nickName);
        User user = new User(nickName, status, role);
        new SqlUserDao().login(user);

        status =  new SqlUserDao().getStatus(nickName);
        Assert.assertEquals("Проверка статуса", status.name(), Status.LOGGEDIN.name());
        logger.info(status.toString());
    }

    @Test
    public void getRoleNotExistUser() {
        Role role =  new SqlUserDao().getRole("not exist");
        Assert.assertEquals("Проверка ролей", role.name(), Role.NOT_EXIST.name());
        logger.info(role.toString());
    }

    @Test
    public void getRoleUserExistUser() {
        Role role =  new SqlUserDao().getRole("test");
        Assert.assertEquals("Проверка ролей", role.name(), Role.USER.name());
        logger.info(role.toString());
    }
}
