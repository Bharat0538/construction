package com.construction.service.construction.repository.security;

import com.construction.service.construction.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}