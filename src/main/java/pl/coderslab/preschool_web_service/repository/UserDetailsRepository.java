package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.UserDetails;

import java.util.ArrayList;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Query(value = "SELECT email2 FROM UserDetails WHERE email LIKE ?1", nativeQuery = true)
    String getEmail2ByEmail(String email);

    @Query(value = "SELECT * FROM UserDetails LEFT JOIN User_roles Ur on UserDetails.id = Ur.User_id WHERE roles NOT LIKE 'ROLE_ADMIN'", nativeQuery = true)
    ArrayList<UserDetails> getAllUsersButNotAdmin();

}
