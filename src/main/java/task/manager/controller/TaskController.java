package task.manager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import task.manager.dto.request.TaskRequestDto;
import task.manager.dto.response.TaskResponseDto;
import task.manager.exception.NoSuchTaskException;
import task.manager.model.Task;
import task.manager.model.User;
import task.manager.service.TaskService;
import task.manager.service.UserService;
import task.manager.service.mapper.TaskDtoMapper;

@Log4j2
@RestController
@RequestMapping("/tasks")
public class TaskController {
   private final TaskService taskService;
   private final UserService userService;
   private final TaskDtoMapper taskDtoMapper;

    public TaskController(TaskService taskService, UserService userService,
                          TaskDtoMapper taskDtoMapper) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskDtoMapper = taskDtoMapper;
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.getByLogin(userDetails.getUsername());
        return taskService.findAllByUser(user).stream()
                .map(taskDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto createTask(Authentication auth,
                                      @RequestBody @Valid TaskRequestDto requestDto) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String login = userDetails.getUsername();
        User user = userService.getByLogin(login);
        Task task = taskDtoMapper.mapToModel(requestDto);
        task.setUser(user);
        Task taskFromDb = taskService.saveTask(task);
        log.info("Task with id " + task.getId() + " was created");
        return taskDtoMapper.mapToDto(taskFromDb);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id,
                                         @RequestBody @Valid TaskRequestDto requestDto,
                                  Authentication auth) {
        Task task = taskService.findTaskById(id);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        if (!task.getUser().getLogin().equals(userDetails.getUsername())) {
            throw new NoSuchTaskException("Task not exist");
        }
        Task requestTask = taskDtoMapper.mapToModel(requestDto);
        task.setCompleted(requestTask.isCompleted());
        task.setDescription(requestTask.getDescription());
        taskService.saveTask(task);
        log.info("Task with id " + id + " was updated");
        return taskDtoMapper.mapToDto(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable Long id, Authentication auth) {
        securityCheck(auth, id);
        taskService.deleteTask(id);
        log.info("Task with id " + id + " was deleted");
    }

    @GetMapping("/completed")
    public List<TaskResponseDto> getAllCompletedTasks(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.getByLogin(userDetails.getUsername());
        return taskService.findAllByUserAndCompleted(user, true).stream()
                .map(taskDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/uncompleted")
    public List<TaskResponseDto> getAllUncompletedTasks(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.getByLogin(userDetails.getUsername());
        return taskService.findAllByUserAndCompleted(user, false).stream()
                .map(taskDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDto completeTask(@PathVariable Long id, Authentication auth) {
        securityCheck(auth, id);
        taskService.completeTask(id);
        log.info("Task with id " + id + " was completed");
        return taskDtoMapper.mapToDto(taskService.findTaskById(id));
    }

    private void securityCheck(Authentication auth, Long id) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        boolean taskByIdAndUserLogin = taskService.findTaskByIdAndUserLogin(id, userDetails.getUsername());
        if (!taskByIdAndUserLogin) {
            throw new NoSuchTaskException("Task not exist");
        }
    }
}
