package com.pltfhs.car.repository;

import com.pltfhs.car.entity.Users;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    public Users findByEmail(String email);

    public Users findByEmailAndIsDeletedIsFalse(String email);

    public List<Users> findByUserTypeAndIsDeletedFalse(String userType, Pageable pageable);

    public Page<Users> findBySystemRolesSet_RoleIdAndIsDeletedFalse(short roleId, Pageable pageable);

    public long countBySystemRolesSet_RoleId(short roleId);

    public Users findBySocialId(String id);
}
