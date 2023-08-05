package com.Chumaengi.chumaengi.board.controller;

import com.Chumaengi.chumaengi.board.controller.dto.BoardRequest;
import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static com.Chumaengi.chumaengi.board.domain.Board.categoryStringToEnum;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{writerId}")
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Void> create(@PathVariable Long writerId,
                                       @RequestBody @Valid BoardRequest request) {
        Long boardId = boardService.create(writerId, request.getTitle(), request.getContent(), request.getCategory());
        return ResponseEntity.created(URI.create("/detail/"+boardId)).build();
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<Void> update(@PathVariable Long writerId, @PathVariable Long boardId,
                                       @RequestBody @Valid BoardRequest request) {
        boardService.update(writerId, boardId, request.getTitle(), request.getContent(), request.getCategory());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable Long writerId, @PathVariable Long boardId) {
        boardService.delete(writerId, boardId);
        return ResponseEntity.ok().build();
    }

    // 게시판 목록 조회
    @GetMapping("/board/list/{categoryNum}")
    public String questionlist(@PathVariable int categoryNum, Model model, @PageableDefault(page=0,size=10)
    Pageable pageable) {
        String categoryName = null;
        if(categoryNum == 1){
            categoryName = "질문";
        }else if(categoryNum == 2){
            categoryName = "공지사항";
        }else if(categoryNum == 3){
            categoryName = "정보공유";
        }

        Page<Board> list = boardService.findByCategory(categoryStringToEnum(categoryName),pageable);

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
            return "board/question_list";
        }else if(categoryNum == 2){
            return "board/boardnotice_list";
        }else {
            return "board/information_list";
        }
    }

}