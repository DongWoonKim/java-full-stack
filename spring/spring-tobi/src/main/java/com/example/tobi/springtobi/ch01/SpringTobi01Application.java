package com.example.tobi.springtobi.ch01;

import com.example.tobi.springtobi.ch01.ex_1_5.dao.CountingConnectionMaker;
import com.example.tobi.springtobi.ch01.ex_1_5.dao.CountingDaoFactory;
import com.example.tobi.springtobi.ch01.ex_1_5.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class SpringTobi01Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        setCharacter();

        ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);
        dao.get("tobi");
        dao.get("tobi");
        dao.get("tobi");


        CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Conn count : " + connectionMaker.getCounter());
    }

    private static void setCharacter() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
