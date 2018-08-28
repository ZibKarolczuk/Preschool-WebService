package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.Child;
import pl.coderslab.preschool_web_service.entity.ChildGroup;
import pl.coderslab.preschool_web_service.entity.UserDetails;
import pl.coderslab.preschool_web_service.entity.security.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findAllByUserDetails(UserDetails userDetails);

    @Query(value="select * from Child where id=?1 and userDetails_id=?2", nativeQuery = true)
    Child getChildWithAuthorisedUser(BigInteger idChild, Long idUser);

    @Query(value = "SELECT id FROM Child WHERE userDetails_id=?1", nativeQuery = true)
    List<BigInteger> getAllChildIdByAuthorisedUser(Long id);

    @Query(value = "SELECT * FROM Child ORDER BY surname ASC", nativeQuery = true)
    List<Child> findAllChilds();

    @Query(value = "SELECT * FROM Child WHERE userDetails_id=?1", nativeQuery = true)
    List<Child> getAllChildsByUser(Long id);

//    List<Child> findAllByUser(User user);

//    @Query(value = "SELECT * FROM Child left join ChildGroup CG on Child.childGroup_id = CG.id where CG.id=?1 order by surname asc", nativeQuery = true)
//    List<Child> getChildByChildGroup();
//
//    List<Child> findAllByChildGroup(ChildGroup childGroup);

    @Query(value = "SELECT * FROM Child order by surname asc", nativeQuery = true)
    List<Child> findAllByChildOrderBySurname();

    @Query(value = "SELECT DISTINCT email FROM Child JOIN User WHERE childGroup_id=?1", nativeQuery = true)
    List<String> getUserEmailByChildGroup(String id);


}
