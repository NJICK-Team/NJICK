package com.sparta.njick.domain.taskState.service;

import com.sparta.njick.domain.taskState.dto.requestDto.TaskStateRequestDto;
import com.sparta.njick.domain.taskState.entity.TaskState;
import com.sparta.njick.domain.taskState.model.TaskStateModel;
import com.sparta.njick.domain.taskState.repository.TaskStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskStateServiceImpl implements TaskStateService {

    private final TaskStateRepository taskStateRepository;
    // private final UserRepository userRepository;

    @Override
    public void createTaskState(TaskStateRequestDto requestDto/*, Long userId*/) {
        //이건 모든 유저... 보드에 있는 사용자만 이어야 함... userRepository.findByIdOrElseThrow(userId);

        TaskStateModel model = taskStateRepository.save(new TaskState(requestDto)).toModel();
    }
}
