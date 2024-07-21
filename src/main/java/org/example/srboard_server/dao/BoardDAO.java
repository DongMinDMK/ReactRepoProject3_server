package org.example.srboard_server.dao;

import jakarta.persistence.EntityManager;
import org.example.srboard_server.dto.Paging;
import org.example.srboard_server.entity.Board;
import org.example.srboard_server.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO {

    @Autowired
    private EntityManager em;
    public List<Board> getBoardList(Paging paging) {
        String sql = "select b from Board b order by b.num desc"; //전체조회

        List<Board> list = em.createQuery(sql, Board.class)
                .setFirstResult(paging.getStartNum()-1)
                .setMaxResults(paging.getDisplayRow())
                .getResultList();
        
        return list;
    }

    public int getAllCount() {
        String sql = "select count(b) from Board b";

        long count = (Long) em.createQuery(sql).getSingleResult();

        return (int)count;
    }

    public Board getBoard(int num) {
        Board board = em.find(Board.class, num);
        return board;
    }

    public List<Reply> getReplyList(int num) {
        String sql = "select b from Reply b where b.boardnum=:bn order by b.replynum desc";

        List<Reply> replyList = em.createQuery(sql, Reply.class)
                .setParameter("bn", num)
                .getResultList();
        return replyList;
    }

    public void addReply(Reply reply) {
        em.persist(reply);
    }

    public void deleteReply(int replynum) {
        Reply reply = em.find(Reply.class, replynum);
        em.remove(reply);
    }

    public void insertBoard(Board board) {
        em.persist(board);
    }

    public void plusReadcount(int num) {
        Board board = em.find(Board.class, num);
        board.setReadcount(board.getReadcount()+1);
    }

    public void updateBoard(Board board) {
        Board board1 = em.find(Board.class, board.getNum());
        board1.setTitle(board.getTitle());
        board1.setContent(board.getContent());
        board1.setImage(board.getImage());
        board1.setSavefilename(board.getSavefilename());
    }

    public void deleteBoard(int num) {
        Board board1 = em.find(Board.class, num);
        em.remove(board1);
    }
}
