package com.will.dao;

import com.will.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("UserDb")
public interface UserDao extends MongoRepository<User, Long> {
    User findByEmail(String email);
}
