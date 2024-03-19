package com.sparta.njick.domain.taskState.repository;

import com.sparta.njick.domain.taskState.entity.TaskState;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStateJpaRepository extends JpaRepository<TaskState, Long> {

    List<TaskState> findAllByBoardId(Long boardId);
}
