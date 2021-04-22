package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.District;
import uz.controlstudentserver.entity.ForeignLanguage;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

}
