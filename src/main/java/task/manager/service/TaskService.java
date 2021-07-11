package task.manager.service;

import java.util.List;
import task.manager.model.Task;
import task.manager.model.User;

public interface TaskService {
    Task saveTask(Task task);

    List<Task> findAllByUser(User user);

    void deleteTask(Long id);

    Task findTaskById(Long id);

    List<Task> findAllByUserAndCompleted(User user, boolean completed);

    void completeTask(Long id);

    boolean findTaskByIdAndUserLogin(Long id, String login);
}
