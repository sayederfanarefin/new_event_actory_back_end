package com.sweetitech.tiger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.tiger.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
