package pl.coderslab.preschool_web_service.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);

    @Query(value = "UPDATE User_roles SET roles = 'ROLE_USER' WHERE User_id = 1? AND roles LIKE 'ROLE_NEWUSER'", nativeQuery = true)
    String updateRoleByUserId(Long id);

}