package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Raport;
import uz.controlstudentserver.entity.Reference;

import java.util.UUID;

@Repository
public interface RaportRepository extends JpaRepository<Raport, UUID> {

}
