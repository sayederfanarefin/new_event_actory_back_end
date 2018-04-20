package com.sweetitech.tiger.service.interfaces;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Comment;
import com.sweetitech.tiger.model.News;


public interface ICommentService {
	
	Comment addComment(Comment comment);
	void deleteComment(Comment comment);
	Comment findById(Long id);
	Page<Comment> findByNews(News news, int page);
//	Comment findByUserId(long userId);
	
	
}
