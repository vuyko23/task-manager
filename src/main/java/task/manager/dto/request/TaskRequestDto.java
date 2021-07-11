package task.manager.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String description;
    private boolean completed;
}
