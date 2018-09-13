package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.preschool_web_service.entity.TextSMS;

public interface TextSMSRepository extends JpaRepository<TextSMS, Long> {

}
