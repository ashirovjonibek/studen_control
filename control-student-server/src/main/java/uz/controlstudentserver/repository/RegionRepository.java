package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Region;

import java.util.UUID;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

}
