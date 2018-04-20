package com.sweetitech.tiger.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    

    @Column(columnDefinition="LONGTEXT")
    private String commentBody;

    @Column(name = "created_at")
    public Date createdAt;
   
   
    @ManyToOne
    @JoinTable(name = "news_comments", joinColumns =@JoinColumn(name = "comment_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"))
    private News news;
  

	public Comment() {
        super();
       
    }

    public Long getId() {
        return id;
    }

    
    public void setId(final Long id) {
        this.id = id;
    }
    

	
	 @Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", commentBody=" + commentBody + ", createdAt=" + createdAt
				+ "]";
	}

	public Comment(Long userId, String commentBody, News news) {
		super();
		this.userId = userId;
		this.commentBody = commentBody;
		this.news = news;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	@PrePersist
	  void createdAt() {
	    this.createdAt = new Date();
	  }

	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.000 ", timezone="UTC")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


}