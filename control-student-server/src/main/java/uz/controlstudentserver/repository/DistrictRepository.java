package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.District;
import uz.controlstudentserver.entity.Region;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
        List<District> findAllByRegion(Region region);
}
