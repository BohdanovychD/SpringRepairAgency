package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.agency.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE login = ?",
            nativeQuery = true)
    User findByLogin(String login);
}
