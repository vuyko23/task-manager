package task.manager.dto.response;

import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String description;
    private boolean completed;
}
