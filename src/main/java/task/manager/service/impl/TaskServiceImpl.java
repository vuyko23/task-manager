package task.manager.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import task.manager.dao.TaskDao;
import task.manager.exception.NoSuchTaskException;
import task.manager.model.Task;
import task.manager.model.User;
import task.manager.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task saveTask(Task task) {
        return taskDao.save(task);
    }

    @Override
    public List<Task> findAllByUser(User user) {
        return taskDao.findAllByUser(user);
    }

    @Override
    public void deleteTask(Long id) {
        taskDao.deleteById(id);
    }

    @Override
    public Task findTaskById(Long id) {
        return taskDao.findTaskById(id).orElseThrow(
                () -> new NoSuchTaskException("Task not exist"));
    }

    @Override
    public List<Task> findAllByUserAndCompleted(User user, boolean completed) {
        return taskDao.findAllByUserAndCompleted(user, completed);
    }

    @Override
    public void completeTask(Long id) {
        taskDao.completeTask(id);
    }

    @Override
    public boolean findTaskByIdAndUserLogin(Long id, String login) {
        return taskDao.findTaskByIdAndUserLogin(id, login);
    }
}
