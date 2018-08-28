package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.preschool_web_service.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
