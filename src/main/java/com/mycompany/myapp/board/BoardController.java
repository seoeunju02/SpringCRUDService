package com.mycompany.myapp.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;


    @RequestMapping(value = "/")
    public  String home(){
        return "login";
    }

    @RequestMapping(value="/board/list", method = RequestMethod.GET)
        public String boardlist(Model model){
        model.addAttribute("list", boardService.getBoardList());
        return "board/list";
    }


    @RequestMapping(value = "board/add",method =RequestMethod.GET)
        public String addPost(){
        return "board/addpostform";
    }

    @RequestMapping(value = "/board/editform/{seq}",method = RequestMethod.GET)
        public String editPost(@PathVariable("seq") int id,Model model){
        BoardVO boardVO= boardService.getBoard(id);
//        System.out.println("boardVO = " + boardVO.getSeq());
        model.addAttribute("boardVO",boardVO);
//        System.out.println("in editPost");
        return "board/editform";
    }
    @RequestMapping(value ="/board/addok",method = RequestMethod.POST)
    public String addPostOK(BoardVO vo){
        int i= boardService.insertBoard(vo);
        if(i==0)
            System.out.println("데이터추가 실패!");
        else
            System.out.println("데이터추가 성공!");
//        return "/board/addok";
        return "redirect:list";
    }

    @RequestMapping(value ="board/editok",method = RequestMethod.POST)
        public String editPostOK(BoardVO vo){
        if(boardService.updateBoard(vo)==0)
            System.out.println("데이터수정 실패!");
        else
            System.out.println("데이터 수정 성공!");
        return "redirect:list";
    }

    @RequestMapping(value = "board/deleteok/{sq}",method = RequestMethod.GET)
    public String deletePost(@PathVariable("sq") int id){
        int i= boardService.deleteBoard(id);
        if(i==0)
            System.out.println("데이터 삭제 실패");
        else
            System.out.println("데이터 삭제 성공!");
        return "redirect:../list";
    }






}
