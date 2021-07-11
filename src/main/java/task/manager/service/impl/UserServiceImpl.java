package task.manager.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task.manager.dao.UserDao;
import task.manager.exception.UserNotExistException;
import task.manager.model.User;
import task.manager.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User user) {
        user.setPassword(UserServiceImpl.getEncoder().encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User getByLogin(String login) {
         return userDao.getUserByLogin(login).orElseThrow(
                () -> new UserNotExistException("Can't find user with login " + login));
    }

    public static PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
