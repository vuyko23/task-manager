package task.manager.service.mapper;

import org.springframework.stereotype.Service;
import task.manager.dto.request.TaskRequestDto;
import task.manager.dto.response.TaskResponseDto;
import task.manager.model.Task;

@Service
public class TaskDtoMapper implements RequestDtoMapper<Task, TaskRequestDto>,
        ResponseDtoMapper<TaskResponseDto, Task> {
    @Override
    public Task mapToModel(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setDescription(taskRequestDto.getDescription());
        task.setCompleted(taskRequestDto.isCompleted());
        return task;
    }

    @Override
    public TaskResponseDto mapToDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setId(task.getId());
        taskResponseDto.setCompleted(task.isCompleted());
        return taskResponseDto;
    }
}
