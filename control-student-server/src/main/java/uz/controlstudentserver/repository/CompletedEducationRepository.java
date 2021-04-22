package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.CompletedEducation;
import uz.controlstudentserver.entity.Country;

@Repository
public interface CompletedEducationRepository extends JpaRepository<CompletedEducation, Integer> {

}
