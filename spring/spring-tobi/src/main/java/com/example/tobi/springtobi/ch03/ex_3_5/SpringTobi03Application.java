package com.example.tobi.springtobi.ch03.ex_3_5;

import com.example.tobi.springtobi.ch03.ex_3_5.dao.DaoFactory;
import com.example.tobi.springtobi.ch03.ex_3_5.dao.UserDao;
import com.example.tobi.springtobi.ch03.ex_3_5.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringTobi03Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("tobi4");
        user.setName("Tobi");
        user.setPassword("1234");

        userDao.add(user);

    }
}
