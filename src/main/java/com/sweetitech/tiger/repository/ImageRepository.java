package com.sweetitech.tiger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.tiger.model.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findById (long id);
    Image findByUrl(String url);
}
