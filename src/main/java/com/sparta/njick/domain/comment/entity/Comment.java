package com.sparta.njick.domain.comment.entity;

import com.sparta.njick.domain.comment.model.CommentModel;
import com.sparta.njick.global.jpa.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@SQLDelete(sql = "update comments set deleted_at = NOW() where comment_id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment extends BaseAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false)
    private Long cardId;

    public Comment(String content, Long userId, Long cardId) {
        this.content = content;
        this.writerId = userId;
        this.cardId = cardId;
    }

    public CommentModel toModel() {
        return CommentModel.builder()
            .commentId(commentId)
            .content(content)
            .writerId(writerId)
            .cardId(cardId)
            .build();
    }
}
