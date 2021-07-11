package task.manager.service.impl;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import task.manager.dao.RoleDao;
import task.manager.model.Role;
import task.manager.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Role findByRoleName(String name) {
        return roleDao.findByRoleName(Role.RoleName.valueOf(name)).orElseThrow(
                () -> new NoSuchElementException("Can't find role by name " + name));
    }
}
