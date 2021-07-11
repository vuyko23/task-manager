package task.manager.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import task.manager.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.roles where u.login =:login")
    Optional<User> getUserByLogin(String login);
}
