package com.sparta.njick.domain.board.controller;

import com.sparta.njick.domain.board.controller.dto.request.BoardParticipateDTO;
import com.sparta.njick.domain.board.controller.dto.request.BoardRegisterDTO;
import com.sparta.njick.domain.board.controller.dto.request.UpdateBoardDTO;
import com.sparta.njick.domain.board.controller.dto.response.ResponseDTO;
import com.sparta.njick.domain.board.service.BoardService;
import com.sparta.njick.domain.user.userDetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<ResponseDTO> searchOwnedBoards(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(ResponseDTO.success(
                boardService.searchAllOwnedBoard(
                        userDetails.getUser().getId(),
                        pageable
                ))
        );
    }

    @GetMapping("/participated")
    public ResponseEntity<ResponseDTO> searchParticipateBoards(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(ResponseDTO.success(
                boardService.searchAllParticipateBoard(
                        userDetails.getUser().getId(),
                        pageable
                ))
        );
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
