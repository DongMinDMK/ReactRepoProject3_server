package org.example.srboard_server.dao;

import jakarta.persistence.EntityManager;
import org.example.srboard_server.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private EntityManager em;


    public Member getMember(String userid) {
        Member member = em.find(Member.class, userid); // 기본키로 레코드를 검색하여 결과를 리턴하는 메서드(where 조건으로 userid 를 걸어서 그거에 맞는 테이블 한 행을 불러오는 코드)
        return member;
    }

    public void insertMember(Member member) {
        em.persist(member); // persist -> 데이터 삽입(entity 를 이용한 레코드 추가)
    }

    public Member updateMember(Member member) {
        Member mem = em.find(Member.class, member.getUserid());

        mem.setPwd(member.getPwd());
        mem.setName(member.getName());
        mem.setEmail(member.getEmail());
        mem.setPhone(member.getPhone());

        return mem;


    }
}
