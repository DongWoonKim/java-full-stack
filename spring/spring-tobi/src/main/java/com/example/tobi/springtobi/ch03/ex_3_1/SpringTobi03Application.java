package com.example.tobi.springtobi.ch03.ex_3_1;

import com.example.tobi.springtobi.ch03.ex_3_1.dao.DaoFactory;
import com.example.tobi.springtobi.ch03.ex_3_1.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTobi03Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        int count = userDao.getCount();
        System.out.println("Count : " + count);


    }
}
