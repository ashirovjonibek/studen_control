package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.controlstudentserver.entity.Subject;

public interface SubjectRepository extends JpaRepository <Subject,Integer> {

}
