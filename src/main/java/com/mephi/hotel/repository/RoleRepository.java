package com.mephi.hotel.repository;

import com.mephi.hotel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Role r set r.name = ?1 where r.idRole = ?2")
    void updRoleName(String roleName, Long roleId);

    @Query("select u from Role u where u.name = ?1")
    Role findByNameCustomQuery(String s);
}
