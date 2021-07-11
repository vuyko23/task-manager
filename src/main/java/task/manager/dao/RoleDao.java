package task.manager.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import task.manager.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(Role.RoleName name);
}
