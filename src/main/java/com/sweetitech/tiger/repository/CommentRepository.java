package com.sweetitech.tiger.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.sweetitech.tiger.model.Comment;
import com.sweetitech.tiger.model.News;


public interface CommentRepository extends JpaRepository<Comment, Long> {

	Comment findById (long id);
	
	
	
	Page<Comment> findByNews(@Param("news") News news, 
            Pageable pageRequest);
	
}
