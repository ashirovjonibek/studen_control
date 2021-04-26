package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.Reference;
import uz.controlstudentserver.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query(value = "select * from users where username=:search",nativeQuery = true)
    List<User> byUsername(String search);

    User findByReference(Reference reference);

}
