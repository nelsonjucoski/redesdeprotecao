package com.api.redesdeprotecao.repositories;


import com.api.redesdeprotecao.enums.RoleName;
import com.api.redesdeprotecao.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
    @Query(value = "SELECT r FROM RoleModel r WHERE r.roleName = :roleName")
    RoleModel findByRoleName(@Param("roleName") RoleName roleName);
}