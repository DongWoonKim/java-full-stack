package com.example.tobi.springtobi.ch03.ex_3_3.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

//    @Bean
//    public UserDao_v1 userDao() {
//        return new UserDao_v1(dataSource());
//    }

    @Bean
    public UserDao_v1 userDao() {
        return new UserDao_v1(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/tobi");
        dataSource.setUsername("root");
        dataSource.setPassword("DongunKim91");
        
        return dataSource;
    }

}
