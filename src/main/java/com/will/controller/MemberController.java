package com.will.controller;

import com.will.entity.Member;
import com.will.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);


    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Member> getAllMembers(){
        List<Member> members = memberService.getAllMembers();
        logger.info("Retrieving all members from database {}", members);
        return members;
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public Member getMemberById(@PathVariable("id") int id){
        Member member = memberService.getMemberById(id);
        logger.info("Retrieving member from database {}", member);
        return member;
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK) //HTTP 200 Force response status - not necessary
    public void removeMemberById(@PathVariable("id") int id){
        logger.info("Removing member from database {}", id);
        memberService.removeMemberById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMember(@RequestBody Member member){
        logger.info("Updateing member in database {}", member);
        memberService.updateMember(member);
    }

    @RequestMapping(value = "/insert/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertMember(@RequestBody Member member){
        logger.info("Inserting new member into database {}", member);
        memberService.insertMember(member);
    }
}
