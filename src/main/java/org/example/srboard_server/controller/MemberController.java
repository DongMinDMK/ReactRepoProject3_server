package org.example.srboard_server.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.srboard_server.entity.Member;
import org.example.srboard_server.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody Member member, HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();
        Member mem = memberService.getMember(member.getUserid());

        if(mem == null){
            hm.put("message", "아이디가 존재하지 않습니다.");
        }else if(!mem.getPwd().equals(member.getPwd())){
            hm.put("message", "비밀번호가 일치하지 않습니다.");
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", mem);
            hm.put("message", "OK");
        }

        return hm;
    }

    @GetMapping("/getLoginUser")
    public HashMap<String, Object> getLoginUser(HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();

        HttpSession session = request.getSession();
        hm.put("loginUser", session.getAttribute("loginUser"));

        return hm;
    }

    @PostMapping("/join")
    public HashMap<String, Object> join(@RequestBody Member member){
        HashMap<String, Object> hm = new HashMap<String, Object>();

        Member mem = memberService.getMember(member.getUserid());
        if(mem != null){
            hm.put("message", "아이디가 중복됩니다.");
        }else{
            memberService.insertMember(member);
            hm.put("message", "OK");
        }

        return hm;
    }

    @GetMapping("/logout")
    public HashMap<String, Object> logout(HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();

        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        hm.put("message", "OK");
        return hm;
    }

    @PostMapping("/updateMember")
    public HashMap<String, Object> updateMember(@RequestBody Member member, HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();

        Member mem = memberService.updateMember(member);

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", mem);


        hm.put("message", "OK");
        return hm;
    }
}
