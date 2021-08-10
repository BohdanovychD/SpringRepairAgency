package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.agency.model.entity.Statement;

import java.util.List;


public interface StatementRepository extends JpaRepository<Statement, Long> {
    @Query(value = "SELECT * FROM statements WHERE user_id = ?",
            nativeQuery = true)
    List<Statement> findByUserId(@Param("userId") long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE statements SET price = ?, status = ? WHERE id = ?",
            nativeQuery = true)
    void setPrice(@Param("price") Double price, @Param("status") String status, @Param("statement_id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE statements SET comment = ?, status = ? WHERE id = ?",
            nativeQuery = true)
    void setComment(@Param("comment") String comment, @Param("status") String status, @Param("statement_id") Long id);
}
