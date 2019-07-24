package com.will.dao;

import com.will.utils.HashPassword;
import com.will.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

/*

 */
@EnableMongoRepositories(basePackageClasses = UserDao.class)
@Configuration
public class UserDaoPopulator {

    //Class mainly used to enter a few sample users here
    @Bean
    CommandLineRunner cmdLineRunner(UserDao userDao){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                //Drop all Users
                userDao.deleteAll();
                ArrayList<String> password = HashPassword.generatePasswordHash("CarefullyChooseAPass");

                userDao.save(new User("clarice@starling.com", password));
                userDao.save(new User("hannible@lector.com", password));
                userDao.save(new User("will@graham.com", password));
                userDao.save(new User("francis@dolarhyde.com", password));
            }
        };


    }
}
