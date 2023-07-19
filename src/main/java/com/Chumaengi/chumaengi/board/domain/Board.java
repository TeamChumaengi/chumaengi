package com.Chumaengi.chumaengi.board.domain;

import com.Chumaengi.chumaengi.board.exception.BoardErrorCode;
import com.Chumaengi.chumaengi.comment.domain.Comment;
import com.Chumaengi.chumaengi.global.BaseTimeEntity;
import com.Chumaengi.chumaengi.global.exception.ChumaengiException;
import com.Chumaengi.chumaengi.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="board")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "view")
    private int view;

    @Column(name = "category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "writer_id", referencedColumnName = "id", nullable = false)
    private Member writer;

    // 게시글 삭제시 달려있는 댓글 모두 삭제
    @OneToMany(mappedBy = "board", cascade = PERSIST, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Board(Member writer, String title, String content, Category category){
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.category = category;
        this.view = 0;
    }

    public static Board createBoard(Member writer, String title, String content, String category){
        return new Board(writer, title, content, categoryStringToEnum(category));
    }

    public void addComment(Member writer, String content){ // 부모 댓글 추가
        commentList.add(Comment.createComment(writer, this, null, content));
    }

    // 입력받은 문자열이 없다면 에러 반환, 있다면 문자열을 enum으로 변환
    private static Category categoryStringToEnum(String categoryValue) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getValue().equals(categoryValue))
                .findFirst()
                .orElseThrow(() -> new ChumaengiException(BoardErrorCode.CATEGORY_NOT_FOUND));
    }

    public void update (String updateTitle, String updateContent, String updateCategory) {
        this.title = updateTitle;
        this.content = updateContent;
        this.category = categoryStringToEnum(updateCategory);
    }
}
