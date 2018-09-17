package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.Child;
import pl.coderslab.preschool_web_service.entity.ChildGroup;

import javax.xml.soap.Detail;
import java.util.List;

public interface ChildGroupRepository extends JpaRepository<ChildGroup, Long> {

    @Query(value = "SELECT LOWER(groupName) FROM ChildGroup ORDER BY groupName ASC", nativeQuery = true)
    List<String> listGroupNameSortedToLowerCase();

    @Query(value = "SELECT * FROM ChildGroup ORDER BY groupName ASC", nativeQuery = true)
    List<ChildGroup> listGroupsSorted();

}
