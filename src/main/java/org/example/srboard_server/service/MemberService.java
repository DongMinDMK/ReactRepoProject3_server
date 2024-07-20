package org.example.srboard_server.service;

import org.example.srboard_server.dao.MemberDAO;
import org.example.srboard_server.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberDAO memberDAO;

    public Member getMember(String userid) {
        Member member = memberDAO.getMember(userid);
        return member;
    }

    public void insertMember(Member member) {
        memberDAO.insertMember(member);
    }

    public Member updateMember(Member member) {
        Member mem = memberDAO.updateMember(member);
        return mem;
    }
}
