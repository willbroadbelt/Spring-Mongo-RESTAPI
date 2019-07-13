package com.will.controller;

import com.will.entity.Member;
import com.will.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Collection<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public Member getMemberById(@PathVariable("id") int id){
        return memberService.getMemberById(id);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK) //HTTP 200 Force response status - not necessary
    public void removeMemberById(@PathVariable("id") int id){
        memberService.removeMemberById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMember(@RequestBody Member member){
        memberService.updateMember(member);
    }

    @RequestMapping(value = "/insert/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertMember(@RequestBody Member member){
        memberService.insertMember(member);
    }
}
