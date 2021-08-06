package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.agency.model.entity.Statement;


public interface StatementRepository extends JpaRepository<Statement, Long> {
    @Query(value = "SELECT * FROM statement WHERE name = ?",
            nativeQuery = true)
    Statement findByName(@Param("name") String name);
}
