package com.sparta.njick.domain.board.service;

import com.sparta.njick.domain.board.controller.dto.request.BoardParticipateDTO;
import com.sparta.njick.domain.board.controller.dto.request.BoardRegisterDTO;
import com.sparta.njick.domain.board.controller.dto.request.UpdateBoardDTO;
import com.sparta.njick.domain.board.service.dto.BoardInfoDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    BoardInfoDTO register(final BoardRegisterDTO dto, final Long requestUserId);

    void participate(final BoardParticipateDTO dto);

    List<BoardInfoDTO> searchAllParticipateBoard(final Long boardId, final Pageable pageable);

    List<BoardInfoDTO> searchAllOwnedBoard(final Long creatorId, final Pageable pageable);

    BoardInfoDTO update(final Long boardId, final UpdateBoardDTO dto, final Long requestId);

    void delete(final Long boardId, final Long requestId);
    BoardInfoDTO searchById(final Long id);
}
