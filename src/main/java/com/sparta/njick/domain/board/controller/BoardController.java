package com.sparta.njick.domain.board.controller;

import com.sparta.njick.domain.board.controller.dto.request.*;
import com.sparta.njick.domain.board.controller.dto.response.ResponseDTO;
import com.sparta.njick.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseDTO> register(
            @RequestBody BoardRegisterDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(ResponseDTO.success(
                boardService.register(
                        dto,
                        userDetails.getUser().getId()
                ))
        );
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> searchOwnedBoards(@RequestBody BoardSearchDTO dto) {
        // TODO: TBD -> will be replace login user id
        return ResponseEntity.ok(ResponseDTO.success(boardService.searchAllOwnedBoard(dto.requestUserId())));
    }

    @GetMapping("/participated")
    public ResponseEntity<ResponseDTO> searchParticipateBoards(@RequestBody BoardSearchDTO dto) {
        // TODO: TBD -> will be replace login user id
        return ResponseEntity.ok(ResponseDTO.success(boardService.searchAllParticipateBoard(dto.requestUserId())));
    }

    @PostMapping("/participate")
    public ResponseEntity<ResponseDTO> participate(
            @RequestBody BoardParticipateDTO dto
    ) {
        boardService.participate(dto);
        return ResponseEntity.ok(ResponseDTO.success(null));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<ResponseDTO> update(
            @PathVariable Long boardId,
            @RequestBody UpdateBoardDTO dto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(ResponseDTO.success(
                boardService.update(
                        boardId,
                        dto,
                        userDetails.getUser().getId()
                ))
        );
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        boardService.delete(boardId, userDetails.getUser().getId());
        return ResponseEntity.ok(ResponseDTO.success(null));
    }
}
