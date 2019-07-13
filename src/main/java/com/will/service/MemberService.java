package com.will.service;

import com.will.dao.MemberDao;
import com.will.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService {

    @Autowired
    @Qualifier("fakeData")
    private MemberDao memberDao;

    public Collection<Member> getAllMembers(){
        return memberDao.getAllMembers();
    }

    public Member getMemberById(int id){
        return memberDao.getMemberById(id);
    }

    public void removeMemberById(int id){
        memberDao.removeMemberById(id);
    }

    public void updateMember(Member member){
        memberDao.updateMember(member);
    }

    public void insertMember(Member member){
        memberDao.insertMember(member);
    }
}
