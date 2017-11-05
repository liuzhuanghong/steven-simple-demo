package com.steven.demo.elasticsearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.steven.demo.elasticsearch.domain.Blog;

public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
	/**
	 * 根据用户名分页查询用户列表
	 * 
	 * @param title
	 * @param content
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
}
