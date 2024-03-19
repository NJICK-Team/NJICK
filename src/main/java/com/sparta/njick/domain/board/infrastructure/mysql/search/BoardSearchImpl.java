package com.sparta.njick.domain.board.infrastructure.mysql.search;

import com.querydsl.jpa.JPQLQuery;
import com.sparta.njick.domain.board.infrastructure.mysql.entity.BoardEntity;
import com.sparta.njick.domain.board.infrastructure.mysql.entity.ParticipationEntity;
import com.sparta.njick.domain.board.infrastructure.mysql.entity.QBoardEntity;
import com.sparta.njick.domain.board.infrastructure.mysql.entity.QParticipationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(BoardEntity.class);
    }

    @Override
    public Page<BoardEntity> searchAllParticipateBoard(Long userId, Pageable pageable) {
        QParticipationEntity participation = QParticipationEntity.participationEntity;
        QBoardEntity board = QBoardEntity.boardEntity;

        JPQLQuery<ParticipationEntity> query = from(participation);
        query.where(participation.participatorId.eq(userId));
        query.leftJoin(board).on(participation.boardId.eq(board.id));
        query.orderBy(participation.createdAt.desc());
        this.getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<BoardEntity> selected = query.select(board);
        List<BoardEntity> fetch = selected.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(fetch, pageable, count);
    }
}
