package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Relations;
import uz.controlstudentserver.entity.ScheduleOfVisits;

import java.util.UUID;

@Repository
public interface RelationsRepository extends JpaRepository<Relations, UUID> {

}
