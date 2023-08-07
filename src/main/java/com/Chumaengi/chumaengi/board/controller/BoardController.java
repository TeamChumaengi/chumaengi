package com.Chumaengi.chumaengi.board.controller;

import com.Chumaengi.chumaengi.board.controller.dto.BoardListResponse;
import com.Chumaengi.chumaengi.board.controller.dto.BoardResponse;
import com.Chumaengi.chumaengi.board.service.BoardService;
import com.Chumaengi.chumaengi.comment.controller.dto.CommentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.Chumaengi.chumaengi.board.domain.Board.categoryStringToEnum;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    // 게시판 목록 조회
    @GetMapping("/list/{categoryNum}")
    public String boardlist(@PathVariable int categoryNum, Model model, @PageableDefault(page=0,size=10)
    Pageable pageable) {
        String categoryName = null;
        if(categoryNum == 1){
            categoryName = "질문";
        }else if(categoryNum == 2){
            categoryName = "정보공유";
        }else if(categoryNum == 3){
            categoryName = "공지사항";
        }

        Page<BoardListResponse> list = boardService.findByCategory(categoryStringToEnum(categoryName),pageable);

        //pageable의 페이지 시작은 0이므로 1로 보기위해 +1
        int nowPage=list.getPageable().getPageNumber()+1;
        //page가 음수가 될 경우 1페이지
        int startPage=Math.max(nowPage-4,1);
        //page가 토탈페이지수를 넘는 경우 마지막페이지
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("boardList",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        if(categoryNum == 1){
            return "boards/question_list";
        }else if(categoryNum == 2){
            return "boards/information_list";
        }else {
            return "boards/boardnotice_list";
        }
    }

    @GetMapping("/detail/{boardId}")
    public String boardDetail(@PathVariable Long boardId, Model model){
        BoardResponse boardResponse = boardService.findById(boardId);
        List<CommentListResponse> commentList = boardResponse.getCommentList();
        if(commentList != null && !commentList.isEmpty()){
            model.addAttribute("commentList",commentList);
        }

        model.addAttribute("board",boardResponse);

        String category = boardResponse.getCategory().getValue();
        if(category.equals("질문")){
            return "boards/question_detail";
        }else if(category.equals("정보공유")){
            return "boards/information_detail";
        }else {
            return "boards/boardnotice_detail";
        }
    }

}
