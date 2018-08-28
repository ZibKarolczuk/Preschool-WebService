package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.ChildGroup;

import java.util.List;

public interface ChildGroupRepository extends JpaRepository<ChildGroup, Long> {

    @Query(value = "SELECT * FROM ChildGroup ORDER BY groupName ASC", nativeQuery = true)
    List<ChildGroup> listGroupsSorted();

    @Query(value = "SELECT groupName FROM ChildGroup ORDER BY groupName ASC", nativeQuery = true)
    List<String> listGroupNameSorted();

    @Query(value = "SELECT LOWER(groupName) FROM ChildGroup ORDER BY groupName ASC", nativeQuery = true)
    List<String> listGroupNameSortedToLowerCase();

    @Query(value = "SELECT COUNT(*) FROM ChildGroup join Child C on ChildGroup.id = C.childGroup_id WHERE childGroup_id=?1", nativeQuery = true)
    int sizeGroupName(Long groupId);

    @Query(value = "SELECT DISTINCT  email FROM ChildGroup RIGHT JOIN Child C on ChildGroup.id = C.childGroup_id " +
            "JOIN User U on C.user_id = U.id JOIN UserDetails Detail on U.userDetails_id = Detail.id WHERE ChildGroup.id = ?1", nativeQuery = true)
    List<String> listEmailGroup(Long grupId);
}
