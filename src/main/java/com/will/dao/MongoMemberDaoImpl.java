package com.will.dao;

import com.will.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Qualifier("mongoData")
public class MongoMemberDaoImpl implements MemberDao {
    @Override
    public Collection<Member> getAllMembers() {
        return null;
    }

    @Override
    public Member getMemberById(int id) {
        return null;
    }

    @Override
    public void removeMemberById(int id) {

    }

    @Override
    public void updateMember(Member member) {

    }

    @Override
    public void insertMember(Member member) {

    }
}
