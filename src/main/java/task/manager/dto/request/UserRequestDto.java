package task.manager.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import task.manager.lib.FieldsMatch;

@FieldsMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 18)
    private String login;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 18)
    private String password;
    private String repeatPassword;
}
