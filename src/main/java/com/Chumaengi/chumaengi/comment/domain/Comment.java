package com.Chumaengi.chumaengi.comment.domain;

import com.Chumaengi.chumaengi.board.domain.Board;
import com.Chumaengi.chumaengi.global.BaseTimeEntity;
import com.Chumaengi.chumaengi.member.domain.Member;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // 부모 댓글 없는 경우 null
    private Comment parent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "writer_id", referencedColumnName = "id", nullable = false)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_id", referencedColumnName = "id", nullable = false)
    private Board board;

    // 부모 댓글 삭제시 달려있는 자식 댓글 모두 삭제
    @OneToMany(mappedBy = "parent", cascade = PERSIST, orphanRemoval = true)
    private List<Comment> childList = new ArrayList<>();

    @Builder
    public Comment(Member writer, Board board, Comment parent, String content){
        this.writer = writer;
        this.board = board;
        this.parent = parent;
        this.content = content;
    }

    public static Comment createComment(Member writer, Board board, Comment parent, String content){
        return new Comment(writer, board, parent, content);
    }

    public void addChildComment(Member writer, Board board, String content) { // 대댓글 추가
        childList.add(Comment.createComment(writer, board, this, content));
    }
}
