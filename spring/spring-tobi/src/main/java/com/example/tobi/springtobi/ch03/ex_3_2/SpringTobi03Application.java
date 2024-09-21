package com.example.tobi.springtobi.ch03.ex_3_2;

import com.example.tobi.springtobi.ch03.ex_3_2.dao.DaoFactory;
import com.example.tobi.springtobi.ch03.ex_3_2.dao.UserDao_v1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTobi03Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao_v1 userDaoV1 = context.getBean("userDao", UserDao_v1.class);



    }
}
