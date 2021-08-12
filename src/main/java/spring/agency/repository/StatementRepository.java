package spring.agency.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.agency.model.entity.Statement;




public interface StatementRepository extends PagingAndSortingRepository<Statement, Long> {

    @Query(value = "SELECT * FROM statements where status != 'COMPLETED' and status !='CANCEL'",
            nativeQuery = true)
    Page<Statement> findAllUncompleted(Pageable pageable);

    @Query(value = "SELECT * FROM statements where status = 'COMPLETED' or status ='CANCEL'",
            nativeQuery = true)
    Page<Statement> findAllCompleted(Pageable pageable);

    @Query(value = "SELECT * FROM statements where status = 'PAID' or status = 'IN A PROCESS' and master_name = ?",
            nativeQuery = true)
    Page<Statement> findAllForMaster(Pageable pageable, @Param("master_name") String masterName);

    @Query(value = "SELECT * FROM statements WHERE user_id = ?",
            nativeQuery = true)
    Page<Statement> findByUserId(@Param("userId") Long userId, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE statements SET price = ?, status = ? WHERE id = ?",
            nativeQuery = true)
    void setPrice(@Param("price") Double price, @Param("status") String status,
                  @Param("statement_id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE statements SET master_name = ?, status = ? WHERE id = ?",
            nativeQuery = true)
    void setMaster(@Param("master_name") String name, @Param("status") String status,
                  @Param("statement_id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE statements SET comment = ?, status = ? WHERE id = ?",
            nativeQuery = true)
    void setComment(@Param("comment") String comment, @Param("status") String status, @Param("statement_id") Long id);
}
