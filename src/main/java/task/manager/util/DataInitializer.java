package task.manager.util;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import task.manager.model.Role;
import task.manager.service.RoleService;

@Component
public class DataInitializer {
    private final RoleService roleService;

    public DataInitializer(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void injectRole() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.save(adminRole);
        roleService.save(userRole);
    }
}
