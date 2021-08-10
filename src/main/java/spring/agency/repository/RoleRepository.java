package spring.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.agency.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT * FROM roles WHERE name = ?",
            nativeQuery = true)
    Role findByRole(@Param("name") String role);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users_roles SET role_id = ? WHERE user_id = ?",
            nativeQuery = true)
    void updateRole(@Param("role_id") Long roleId, @Param("user_id") Long userId);
}
