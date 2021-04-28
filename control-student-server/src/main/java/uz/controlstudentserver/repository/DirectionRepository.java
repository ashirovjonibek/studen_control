package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.controlstudentserver.entity.Direction;
import uz.controlstudentserver.entity.Faculty;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction,Integer> {
    List<Direction> findAllByFaculty(Faculty faculty);
}
