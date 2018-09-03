package pl.coderslab.preschool_web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.preschool_web_service.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM Message WHERE sendTo NOT LIKE ?1 ORDER BY Id DESC", nativeQuery = true)
    List<Message> listMessagesOut(String discardEmail);

    @Query(value = "SELECT * FROM Message WHERE sendFrom NOT LIKE ?1 ORDER BY Id DESC", nativeQuery = true)
    List<Message> listMessagesIn(String discardEmail);

}
