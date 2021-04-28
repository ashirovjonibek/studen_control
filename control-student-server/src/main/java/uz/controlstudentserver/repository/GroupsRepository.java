package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.controlstudentserver.entity.Direction;
import uz.controlstudentserver.entity.Groups;
import uz.controlstudentserver.entity.User;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups,Integer> {
    List<Groups> findAllByTeachers(User user);

    List<Groups> findAllByDirection(Direction direction);
}
