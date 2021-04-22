package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Role;
import uz.controlstudentserver.entity.ScheduleOfVisits;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
