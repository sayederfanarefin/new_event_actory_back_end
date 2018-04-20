package com.sweetitech.tiger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.tiger.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
