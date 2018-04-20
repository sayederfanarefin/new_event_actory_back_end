package com.sweetitech.tiger.repository.ecommerce;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.ecommerce.Ptag;

@Repository("ptagRepository")
public interface PTagRepository extends JpaRepository<Ptag, Long> {
	Ptag findById(long id);
	Ptag findByTag(String tag);
	//Page<Ptag> findAllByTag(String[] tags);
}