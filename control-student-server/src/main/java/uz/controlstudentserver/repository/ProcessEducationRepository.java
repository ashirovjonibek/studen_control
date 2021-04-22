package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.ProcessEducation;
import uz.controlstudentserver.entity.Region;

@Repository
public interface ProcessEducationRepository extends JpaRepository<ProcessEducation, Integer> {

}
