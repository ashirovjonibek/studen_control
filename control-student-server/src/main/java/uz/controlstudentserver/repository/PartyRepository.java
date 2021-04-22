package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Party;
import uz.controlstudentserver.entity.ProcessEducation;

@Repository
public interface PartyRepository extends JpaRepository<Party, Integer> {

}
