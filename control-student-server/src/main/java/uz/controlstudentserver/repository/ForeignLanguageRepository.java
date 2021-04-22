package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.ForeignLanguage;
import uz.controlstudentserver.entity.LaborActivity;

@Repository
public interface ForeignLanguageRepository extends JpaRepository<ForeignLanguage, Integer> {

}
