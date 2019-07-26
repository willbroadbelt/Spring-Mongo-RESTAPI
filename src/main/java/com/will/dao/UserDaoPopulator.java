package com.will.dao;

import com.will.utils.HashPassword;
import com.will.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
                List<String> locations = new ArrayList<>();
                locations.add("Liverpool,UK");
                locations.add("Washington,USA");

                userDao.save(new User("clarice@starling.com", password, locations));
                userDao.save(new User("hannible@lector.com", password, locations));
                userDao.save(new User("will@graham.com", password, locations));
                userDao.save(new User("francis@dolarhyde.com", password, locations));
            }
        };


    }
}
