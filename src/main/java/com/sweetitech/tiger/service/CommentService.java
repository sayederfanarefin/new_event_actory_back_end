package com.sweetitech.tiger.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.Comment;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.repository.CommentRepository;
import com.sweetitech.tiger.service.interfaces.ICommentService;


@Service
@Transactional
public class CommentService implements ICommentService {

	@Autowired
	CommentRepository commentRepository;
	
	
	@Override
	public Comment addComment(Comment comment) {
		
			return commentRepository.save(comment);
	}
	@Override
	public void deleteComment(Comment comment) {
		
		commentRepository.delete(comment);
		
	}
	@Override
	public Comment findById(Long id) {
		
		return commentRepository.findById(id);
	}
	@Override
	public Page<Comment> findByNews(News news, int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "createdAt");
		
	        return commentRepository.findByNews(news, request);
	        
		
	}
	
	
	
//	@Override
//	public Comment findByUserId(long userId) {
//		
//		return commentRepository.findByUserId(userId);
//	}
//	
//	
//	
	

}
