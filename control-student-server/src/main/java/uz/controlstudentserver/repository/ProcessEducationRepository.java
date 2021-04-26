package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Education;

@Repository
public interface ProcessEducationRepository extends JpaRepository<Education, Integer> {

}
