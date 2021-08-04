package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.agency.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT * FROM roles WHERE name = ?",
            nativeQuery = true)
    Role findByName(@Param("name") String name);
}
