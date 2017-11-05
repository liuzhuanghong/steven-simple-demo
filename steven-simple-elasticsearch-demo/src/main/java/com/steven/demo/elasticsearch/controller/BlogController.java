package com.steven.demo.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.steven.demo.elasticsearch.domain.Blog;
import com.steven.demo.elasticsearch.repository.BlogRepository;

/**
 * Blog控制类
 * 
 * @author Steven
 *
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {
	@Autowired
	private BlogRepository blogRepository;

	@GetMapping
	public List<Blog> list(@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "content", required = false, defaultValue = "") String content,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

		// http://localhost:7000/blogs?title=%E4%B8%AD&content=%E5%90%95%E5%B8%83
		//http://localhost:7000/blogs?title=中&content=吕布
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		Page<Blog> page = blogRepository.findByTitleLikeOrContentLike(title, content, pageable);

		return page.getContent();
	}
}
