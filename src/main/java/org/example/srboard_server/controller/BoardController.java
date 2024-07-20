package org.example.srboard_server.controller;

import jakarta.servlet.ServletContext;
import org.example.srboard_server.dto.Paging;
import org.example.srboard_server.entity.Board;
import org.example.srboard_server.entity.Reply;
import org.example.srboard_server.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/getBoardList/{page}")
    public HashMap<String, Object> getBoardList(@PathVariable("page") int page){
        HashMap<String, Object> hm = new HashMap<>();

        Paging paging = new Paging();
        paging.setPage(page);

        int count = boardService.getAllCount();
        paging.setTotalCount(count);
        paging.calPaging();

        hm.put("boardList", boardService.getBoardList(paging));
        hm.put("paging", paging);

        return hm;
    }

    @GetMapping("/getBoard/{num}")
    public HashMap<String, Object> getBoard(@PathVariable("num") int num){
        HashMap<String, Object> hm = new HashMap<>();

        Board board = boardService.getBoard(num);

        hm.put("board", board);

        return hm;
    }

    @GetMapping("/getReplyList/{num}")
    public HashMap<String, Object> getReplyList(@PathVariable("num") int num){
        HashMap<String, Object> hm = new HashMap<>();

        hm.put("replyList", boardService.getReplyList(num));

        return hm;
    }

    @PostMapping("/addReply")
    public HashMap<String, Object> addReply(@RequestBody Reply reply){
        HashMap<String, Object> hm = new HashMap<>();

        boardService.addReply(reply);

        hm.put("message", "OK");

        return hm;
    }

    @GetMapping("/deleteReply/{replynum}")
    public HashMap<String, Object> deleteReply(@PathVariable("replynum") int replynum){
        HashMap<String, Object> hm = new HashMap<>();

        boardService.deleteReply(replynum);


        hm.put("message", "OK");

        return hm;
    }

    @Autowired
    ServletContext servletContext;

    @PostMapping("/fileUpload")
    public HashMap<String, Object> fileUpLoad(@RequestParam("image") MultipartFile file){
        HashMap<String, Object> hm = new HashMap<>();

        String path = servletContext.getRealPath("/images");

        Calendar today = Calendar.getInstance();
        long dt = today.getTimeInMillis();

        String filename = file.getOriginalFilename();
        String fn1 = filename.substring(0, filename.indexOf("."));
        String fn2 = filename.substring(filename.indexOf("."));
        String uploadPath = path + "/" + fn1 + dt + fn2;

        try{
            file.transferTo(new File(uploadPath));
            hm.put("image", filename);
            hm.put("savefilename", fn1 + dt + fn2);
        }catch(IllegalStateException | IOException e){
            e.printStackTrace();
        }
        return hm;
    }

    @PostMapping("/insertBoard")
    public HashMap<String, Object> insertBoard(@RequestBody Board board){
        HashMap<String, Object> hm = new HashMap<>();

        boardService.insertBoard(board);

        hm.put("message", "OK");

        return hm;
    }

    @PostMapping("/plusReadcount/{num}")
    public HashMap<String, Object> plusReadcount(@PathVariable("num") int num){
        HashMap<String, Object> hm = new HashMap<>();

        boardService.plusReadcount(num);

        hm.put("message", "OK");

        return hm;
    }


}
