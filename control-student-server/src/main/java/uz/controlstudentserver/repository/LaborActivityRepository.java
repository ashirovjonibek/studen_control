package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.LaborActivity;
import uz.controlstudentserver.entity.Party;

@Repository
public interface LaborActivityRepository extends JpaRepository<LaborActivity, Integer> {

}
