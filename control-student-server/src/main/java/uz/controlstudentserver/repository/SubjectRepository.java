package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.controlstudentserver.entity.Subject;
import uz.controlstudentserver.entity.User;

import java.util.List;

public interface SubjectRepository extends JpaRepository <Subject,Integer> {
    List<Subject> findAllByTeachers(User user);
    boolean existsByName(String name);

}
