package uz.controlstudentserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.controlstudentserver.entity.AttachmentContent;
import uz.controlstudentserver.entity.CompletedEducation;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

}