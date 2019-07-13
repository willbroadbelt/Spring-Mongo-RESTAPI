package com.will.dao;

import com.will.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier("mongoDb")
public interface MongoDaoRepo extends MongoRepository<Member, Integer> {

}
