package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Raport;
import uz.controlstudentserver.entity.Reference;
import uz.controlstudentserver.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface RaportRepository extends JpaRepository<Raport, UUID> {
    List<Raport> findAllByStudent(User user);
    List<Raport> findAllByReporter(User user);
}
