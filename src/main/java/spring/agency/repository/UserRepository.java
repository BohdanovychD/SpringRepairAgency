package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.agency.model.entity.User;

import java.util.Collection;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE login = ?",
            nativeQuery = true)
    User findByLogin(@Param("login") String login);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE users SET balance = ? WHERE id = ?",
            nativeQuery = true)
    void updateBalance(@Param("balance") Double balance, @Param("user_id") Long id);

    @Query(value = "SELECT * FROM users_roles where role_id = ?",
            nativeQuery = true)
    List<Long> findAllUserIdByRoleId(@Param("role_id") Long id);

}
