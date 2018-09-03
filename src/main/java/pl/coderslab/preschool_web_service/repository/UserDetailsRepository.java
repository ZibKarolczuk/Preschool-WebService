package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query(value = "SELECT email2 FROM UserDetails WHERE email LIKE ?1", nativeQuery = true)
    String getEmail2ByEmail(String email);

}
