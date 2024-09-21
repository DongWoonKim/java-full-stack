package com.example.tobi.springtobi.ch03.ex_3_3;

import com.example.tobi.springtobi.ch03.ex_3_3.dao.DaoFactory;
import com.example.tobi.springtobi.ch03.ex_3_3.dao.UserDao_v1;
import com.example.tobi.springtobi.ch03.ex_3_3.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringTobi03Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao_v1 userDao = context.getBean("userDao", UserDao_v1.class);

        User user = new User();
        user.setId("tobi");
        user.setName("Tobi");
        user.setPassword("1234");

        userDao.add(user);

    }
}
