package task.manager.service;

import task.manager.model.User;

public interface AuthenticationService {
    void register(String login, String password);
}
