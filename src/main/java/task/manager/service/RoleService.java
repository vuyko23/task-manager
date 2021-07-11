package task.manager.service;

import task.manager.model.Role;

public interface RoleService {
    Role save(Role role);

    Role findByRoleName(String name);
}
