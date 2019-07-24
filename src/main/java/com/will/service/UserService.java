package com.will.service;

import com.will.dao.UserDao;
import com.will.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    @Qualifier("UserDb")
    private UserDao userDao;


    //TODO: Check user/email already present
    public void insertUser(User user){
        try{
            userDao.insert(user);
            logger.info("Inserting new user success");
        } catch (Exception e){
            logger.info("Inserting new user failed");
        }

    }

    public User getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    public void updateUser(User user){userDao.save(user);}
    public void removeUser(User user){ userDao.delete(user);}
}
