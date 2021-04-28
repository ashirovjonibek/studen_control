package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.controlstudentserver.entity.Education;

public interface EducationRepository extends JpaRepository<Education,Integer> { 
}
