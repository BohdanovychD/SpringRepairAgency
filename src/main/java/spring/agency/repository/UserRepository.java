package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.agency.model.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE login = ?",
            nativeQuery = true)
    User findByLogin(@Param("login") String login);

}
