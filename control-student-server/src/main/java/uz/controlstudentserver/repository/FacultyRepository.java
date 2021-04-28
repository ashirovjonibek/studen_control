package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.controlstudentserver.entity.Education;
import uz.controlstudentserver.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    List<Faculty> findAllByEducation(Education education);
}
