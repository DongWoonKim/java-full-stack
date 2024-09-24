package com.example.tobi.springtobi.ch05.ex_5_1;

import com.example.tobi.springtobi.ch05.ex_5_1.dao.DaoFactory;
import com.example.tobi.springtobi.ch05.ex_5_1.dao.UserDao;
import com.example.tobi.springtobi.ch05.ex_5_1.domain.Level;
import com.example.tobi.springtobi.ch05.ex_5_1.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringTobiApplication {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user1 = new User();
        user1.setId("tobi6");
        user1.setName("Tobi");
        user1.setPassword("1234");
        user1.setLevel(Level.BASIC);
        user1.setLogin(1);
        user1.setRecommend(0);

        User user2 = new User();
        user2.setId("tobi7");
        user2.setName("Tobi");
        user2.setPassword("1234");
        user2.setLevel(Level.SILVER);
        user2.setLogin(55);
        user2.setRecommend(10);

        User user3 = new User();
        user3.setId("tobi8");
        user3.setName("Tobi");
        user3.setPassword("1234");
        user3.setLevel(Level.GOLD);
        user3.setLogin(100);
        user3.setRecommend(40);

//        userDao.add(user1);
//        userDao.add(user2);
//        userDao.add(user3);

        User user4 = new User();
        user4.setId("tobi8");
        user4.setName("Tobi888");
        user4.setPassword("1234888");
        user4.setLevel(Level.GOLD);
        user4.setLogin(100);
        user4.setRecommend(40);

//        userDao.update(user4);

    }
}
