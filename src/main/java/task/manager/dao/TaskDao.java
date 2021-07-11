package task.manager.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import task.manager.model.Task;
import task.manager.model.User;

public interface TaskDao extends JpaRepository<Task, Long> {
    List<Task> findAllByUser(User user);

    Optional<Task> findTaskById(Long id);

    List<Task> findAllByUserAndCompleted(User user, boolean completed);

    @Modifying
    @Transactional
    @Query("update Task t set t.completed = true where t.id = :id")
    void completeTask(@Param("id") Long id);

    @Query("select case when count(t) > 0 then true else false end "
            + "from Task t where t.id =:id and t.user.login =:login")
    boolean findTaskByIdAndUserLogin(@Param("id") Long id, @Param("login") String login);
}
