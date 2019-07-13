package com.will.dao;

import com.will.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
* Psuedo database for members
*
* */
@Repository
@Qualifier("fakeData")
public class FakeMemberDaoImpl implements MemberDao {

    private static Map<Integer, Member> members;

    static{

        members = new HashMap<Integer, Member>(){

            {
                put(1, new Member(1, "Will", "Free"));
                put(2, new Member(2, "John", "Free"));
                put(3, new Member(3, "James", "Premium"));

            }

        };
    }
    @Override
    public Collection<Member> getAllMembers(){
        return this.members.values();
    }

    @Override
    public Member getMemberById(int id){
        return this.members.get(id);
    }

    @Override
    public void removeMemberById(int id){
        this.members.remove(id);
    }

    @Override
    public void updateMember(Member member){
        Member m = this.members.get(member.getId());
        m.setName(member.getName());
        m.setType(member.getType());
        this.members.put(member.getId(), member);

    }

    @Override
    public void insertMember(Member member){
        this.members.put(member.getId(), member);
    }

}
