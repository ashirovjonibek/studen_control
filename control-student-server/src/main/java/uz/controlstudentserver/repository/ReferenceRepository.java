package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Reference;
import uz.controlstudentserver.entity.Relations;

import java.util.UUID;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, UUID> {

}
