package com.pooh.notebook.repository;

import com.pooh.notebook.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
     Role findRoleByName(String name);
}
