package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Groups;
import uz.controlstudentserver.entity.Reference;
import uz.controlstudentserver.entity.Relations;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, UUID> {
       List<Reference> findAllByGroups(Groups groups);
}
