package com.will.dao;

import com.will.entity.Member;

import java.util.Collection;

public interface MemberDao {
    Collection<Member> getAllMembers();

    Member getMemberById(int id);

    void removeMemberById(int id);

    void updateMember(Member member);

    void insertMember(Member member);
}
