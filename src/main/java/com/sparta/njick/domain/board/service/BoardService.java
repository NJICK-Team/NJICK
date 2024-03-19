package com.sparta.njick.domain.board.service;

import com.sparta.njick.domain.board.controller.dto.request.BoardRegisterDTO;
import com.sparta.njick.domain.board.controller.dto.request.DeleteBoardDTO;
import com.sparta.njick.domain.board.controller.dto.request.UpdateBoardDTO;
import com.sparta.njick.domain.board.service.dto.BoardInfoDTO;
import com.sparta.njick.domain.board.controller.dto.request.BoardParticipateDTO;

import java.util.List;

public interface BoardService {
    BoardInfoDTO register(final BoardRegisterDTO dto);
    void participate(final BoardParticipateDTO dto);
    List<BoardInfoDTO> searchAllParticipateBoard(final Long boardId);
    List<BoardInfoDTO> searchAllOwnedBoard(final Long creatorId);
    BoardInfoDTO update(final UpdateBoardDTO dto);
    void delete(final DeleteBoardDTO dto);
}
