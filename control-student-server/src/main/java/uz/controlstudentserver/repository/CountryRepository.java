package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Country;
import uz.controlstudentserver.entity.District;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
