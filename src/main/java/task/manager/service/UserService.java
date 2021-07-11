package task.manager.service;

import task.manager.model.User;

public interface UserService {
    User save(User user);

    User getByLogin(String login);
}
