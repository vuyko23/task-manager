package task.manager.service.impl;

import java.util.Set;
import org.springframework.stereotype.Service;
import task.manager.model.User;
import task.manager.service.AuthenticationService;
import task.manager.service.RoleService;
import task.manager.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void register(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRoles(Set.of(roleService.findByRoleName("USER")));
        userService.save(user);
    }
}
