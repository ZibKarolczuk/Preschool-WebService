package pl.coderslab.preschool_web_service.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.security.User;


public interface UserRepository extends JpaRepository<User, Long> {
    pl.coderslab.preschool_web_service.entity.security.User findByEmail(String email);
    void delete(User user);

    User findByUsername(String username);

    @Query(value = "SELECT roles FROM User LEFT JOIN User_roles Ur on User.id = Ur.User_id WHERE username = 1?", nativeQuery = true)
    String userDispatchJSP(String username);

}