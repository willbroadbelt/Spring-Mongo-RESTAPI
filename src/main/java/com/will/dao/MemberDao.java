package com.will.dao;

import com.will.entity.Member;

import java.util.Collection;
import java.util.List;

public interface MemberDao {
    List<Member> findAll();

    Member findById(int id);

    void removeById(int id);

    //Update
    void save(Member member);

    void insert(Member member);
}
