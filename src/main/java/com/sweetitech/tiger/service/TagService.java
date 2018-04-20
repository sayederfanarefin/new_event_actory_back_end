package com.sweetitech.tiger.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.model.Banner;
import com.sweetitech.tiger.model.Tag;
import com.sweetitech.tiger.repository.TagRepository;
import com.sweetitech.tiger.service.interfaces.ITagService;

@Service
@Transactional
public class TagService implements ITagService {

	@Autowired
	TagRepository tagRepository;

	@Override
	public Tag addTag(Tag tag) {

		return tagRepository.save(tag);
	}

	@Override
	public Tag findById(Long id) {

		return tagRepository.findById(id);
	}

	@Override
	public Tag findByTag(String tag) {

		return tagRepository.findByTag(tag);
	}

	@Override
	public List<Tag> findAll() {

		return tagRepository.findAll();
	}

	@Override
	public Tag updateTag(Tag tag) {

		return tagRepository.save(tag);
	}

	@Override
	public void deleteTag(Tag tag) {

		tagRepository.delete(tag);
	}

}
