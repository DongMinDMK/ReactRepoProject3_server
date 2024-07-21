package org.example.srboard_server.service;

import org.example.srboard_server.dao.BoardDAO;
import org.example.srboard_server.dto.Paging;
import org.example.srboard_server.entity.Board;
import org.example.srboard_server.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardService {

    @Autowired
    BoardDAO boardDAO;

    public List<Board> getBoardList(Paging paging) {
        List<Board> list = boardDAO.getBoardList(paging);

        return list;
    }

    public int getAllCount() {
        int count = boardDAO.getAllCount();
        return count;
    }

    public Board getBoard(int num) {
        Board board = boardDAO.getBoard(num);
        return board;
    }

    public List<Reply> getReplyList(int num) {
        List<Reply> replyList = boardDAO.getReplyList(num);
        return replyList;
    }

    public void addReply(Reply reply) {
        boardDAO.addReply(reply);
    }

    public void deleteReply(int replynum) {

        boardDAO.deleteReply(replynum);
    }

    public void insertBoard(Board board) {
        boardDAO.insertBoard(board);
    }

    public void plusReadcount(int num) {
        boardDAO.plusReadcount(num);
    }

    public void updateBoard(Board board) {
        boardDAO.updateBoard(board);
    }

    public void deleteBoard(int num) {
        boardDAO.deleteBoard(num);
    }
}
