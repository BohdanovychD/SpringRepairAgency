package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.agency.model.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE login = ?",
            nativeQuery = true)
    User findByLogin(@Param("login") String login);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE users SET balance = ? WHERE user_id = ?",
            nativeQuery = true)
    void updateBalance(@Param("balance") Double balance, @Param("user_id") Long id);
}
