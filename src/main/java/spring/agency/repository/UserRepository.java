package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.agency.entity.roles.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
