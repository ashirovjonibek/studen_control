package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.ScheduleOfVisits;
import uz.controlstudentserver.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleOfVisitsRepository extends JpaRepository<ScheduleOfVisits, UUID> {
    List<ScheduleOfVisits> findAllByStudent(User user);
}
