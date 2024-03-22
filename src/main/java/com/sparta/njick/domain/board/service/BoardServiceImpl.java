package com.sparta.njick.domain.board.service;

import com.sparta.njick.domain.board.controller.dto.request.BoardParticipateDTO;
import com.sparta.njick.domain.board.controller.dto.request.BoardRegisterDTO;
import com.sparta.njick.domain.board.controller.dto.request.UpdateBoardDTO;
import com.sparta.njick.domain.board.model.Board;
import com.sparta.njick.domain.board.repository.BoardRepository;
import com.sparta.njick.domain.board.service.dto.BoardInfoDTO;
import com.sparta.njick.global.exception.CustomRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sparta.njick.global.exception.errorcode.BoardErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardInfoDTO register(final BoardRegisterDTO dto, final Long creatorId) {
        Board registered = boardRepository.register(
                dto.title(),
                dto.description(),
                dto.color(),
                creatorId
        );

        return BoardInfoDTO.from(registered);
    }

    public BoardInfoDTO searchById(final Long id) {
        return BoardInfoDTO.from(boardRepository.findById(id));
    }

    @Override
    public void participate(final BoardParticipateDTO dto) {
        if (boardRepository.findById(dto.boardId()) == null) {
            throw new CustomRuntimeException(BOARD_NOT_FOUND.getMessage());
        }
        if (boardRepository.isParticipated(dto.boardId(), dto.participatorId())) {
            throw new CustomRuntimeException(DUPLICATE_PARTICIPATION.getMessage());
        }

        boardRepository.participate(dto.boardId(), dto.participatorId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BoardInfoDTO> searchAllParticipateBoard(final Long userId, final Pageable pageable) {
        // TODO: replace to PageRequestDTO
        return boardRepository.searchAllParticipateBoard(userId, pageable)
                .stream()
                .map(BoardInfoDTO::from)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BoardInfoDTO> searchAllOwnedBoard(final Long creatorId, final Pageable pageable) {
        // TODO: replace to PageRequestDTO
        return boardRepository.searchAllByCreatorId(creatorId, pageable)
                .stream()
                .map(BoardInfoDTO::from)
                .toList();
    }

    @Override
    public BoardInfoDTO update(final Long boardId, final UpdateBoardDTO dto, final Long requestUserId) {
        Board board = boardRepository.findById(boardId);
        Board updateBoard = board.update(
                dto.title(),
                dto.description(),
                dto.color(),
                requestUserId
        );

        return BoardInfoDTO.from(boardRepository.update(updateBoard));
    }

    @Override
    public void delete(final Long boardId, final Long requestUserId) {
        Board board = boardRepository.findById(boardId);

        if (board.isNotOwner(requestUserId)) {
            throw new CustomRuntimeException(UN_AUTHORIZED.getMessage());
        }

        boardRepository.delete(boardId);
    }
}
