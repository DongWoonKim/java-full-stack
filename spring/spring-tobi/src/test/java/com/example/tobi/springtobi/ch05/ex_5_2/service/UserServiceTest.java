package com.example.tobi.springtobi.ch05.ex_5_2.service;

import com.example.tobi.springtobi.ch05.ex_5_2.domain.Level;
import com.example.tobi.springtobi.ch05.ex_5_2.dao.DaoFactory;
import com.example.tobi.springtobi.ch05.ex_5_2.dao.UserDao;
import com.example.tobi.springtobi.ch05.ex_5_2.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DaoFactory.class)
class UserServiceTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager transactionManager;

    List<User> users;

    @BeforeEach
    public void setUp() {
        userDao.deleteAll();
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, 49, 0),
                new User("joytouch", "강명성", "p2", Level.BASIC, 50, 0),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, 29),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, 30),
                new User("green", "오민규", "p5", Level.GOLD, 100, 100)
        );
    }

    @Test
    void upgradeAllOrNothing() {
        UserService testUserService = new UserService.TestUserService( users.get(3).getId() );
        testUserService.setUserDao(this.userDao);

        userDao.deleteAll();

        for (User user : users) {
            userDao.add(user);
        }

        System.out.println(userDao.get("joytouch").getLevel());
        try {
            testUserService.upgradeLevels();
        } catch (Exception e) {}


        System.out.println(userDao.get("joytouch").getLevel());
    }

    @Test
    void upgradeAllOrNothing_v2() {
        UserService testUserService = new UserService.TestUserService( users.get(3).getId() );
        testUserService.setUserDao(this.userDao);
        testUserService.setDataSource(dataSource);

        userDao.deleteAll();

        for (User user : users) {
            userDao.add(user);
        }

        System.out.println(userDao.get("joytouch").getLevel());
        try {
            testUserService.upgradeLevelsV2();
        } catch (Exception e) {}


        System.out.println(userDao.get("joytouch").getLevel());
    }

    @Test
    void upgradeAllOrNothing_v3() {
        UserService testUserService = new UserService.TestUserService( users.get(3).getId() );
        testUserService.setUserDao(this.userDao);
        testUserService.setDataSource(dataSource);

        userDao.deleteAll();

        for (User user : users) {
            userDao.add(user);
        }

        System.out.println(userDao.get("joytouch").getLevel());
        try {
            testUserService.upgradeLevelsV3();
        } catch (Exception e) {}


        System.out.println(userDao.get("joytouch").getLevel());
    }

    @Test
    void upgradeAllOrNothing_v4() {
        UserService testUserService = new UserService.TestUserService( users.get(3).getId() );
        testUserService.setUserDao(this.userDao);
        testUserService.setTransactionManager(transactionManager);

        userDao.deleteAll();

        for (User user : users) {
            userDao.add(user);
        }

        System.out.println(userDao.get("joytouch").getLevel());
        try {
            testUserService.upgradeLevelsV4();
        } catch (Exception e) {}


        System.out.println(userDao.get("joytouch").getLevel());
    }
}