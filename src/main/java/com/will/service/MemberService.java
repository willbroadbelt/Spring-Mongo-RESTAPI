package com.will.service;

import com.will.dao.MongoDaoRepo;
import com.will.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    @Qualifier("mongoDb")
    private MongoDaoRepo memberDao;


    public List<Member> getAllMembers(){
        return memberDao.findAll();
    }

    public Member getMemberById(int id){ return memberDao.findById(id).get(); }

    public void removeMemberById(int id){
        memberDao.deleteById(id);
    }

    public void updateMember(Member member){
        memberDao.save(member);
    }

    public void insertMember(Member member){
        memberDao.insert(member);
    }
}
