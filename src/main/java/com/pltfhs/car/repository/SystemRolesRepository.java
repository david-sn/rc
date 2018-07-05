package com.pltfhs.car.repository;

import com.pltfhs.car.entity.SystemRoles;
import com.pltfhs.car.entity.Users;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRolesRepository extends JpaRepository<SystemRoles, Short> {

    List<Users> findAllUsersByRoleId(short id, Pageable page);
}
