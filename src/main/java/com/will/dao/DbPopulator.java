package com.will.dao;

import com.will.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongoRepositories(basePackageClasses = MongoDaoRepo.class)
//@Configuration
public class DbPopulator {

    @Bean
    CommandLineRunner cmdLineRunner(MongoDaoRepo memberRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                //Drop all Members
                memberRepository.deleteAll();

                memberRepository.save(new Member(1, "Clarice", "Investigator"));
                memberRepository.save(new Member(2, "Hannible Lector", "Cannible"));
                memberRepository.save(new Member(3, "Will Graham", "Investigator"));
                memberRepository.save(new Member(4, "Francis Dolarhyde", "Serial"));
            }
        };


    }
}
