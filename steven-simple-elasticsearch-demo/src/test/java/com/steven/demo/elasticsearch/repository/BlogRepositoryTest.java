package com.steven.demo.elasticsearch.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.steven.demo.elasticsearch.domain.Blog;

/**
 * BlogRepository测试类
 * 
 * @author Steven
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogRepositoryTest {

	@Autowired
	private BlogRepository blogRepository;

	@Test
	public void testFindByTitleLikeOrContentLike() {
		System.out.println("start..");
		// 清空所有
		blogRepository.deleteAll();
		blogRepository
				.save(new Blog("1", "三国演义第一回", "宴桃园豪杰三结义　斩黄巾英雄首立功"));
		blogRepository.save(
				new Blog("2", "三国演义第二回", "张翼德怒鞭督邮　何国舅谋诛宦竖")); 
		blogRepository.save(
				new Blog("3", "三国演义第三回", "议温明董卓叱丁原　馈金珠李肃说吕布")); 
		blogRepository.save(
				new Blog("4", "三国演义第四回", "废汉帝陈留践位　谋董贼孟德献刀")); 
		blogRepository.save(
				new Blog("5", "三国演义第五回", "发矫诏诸镇应曹公　破关兵三英战吕布")); 
		System.out.println("find..");
		Pageable pageable = new PageRequest(0, 20);
		Page<Blog> page = blogRepository.findByTitleLikeOrContentLike("五", "吕布", pageable);
		
		System.out.println("getTotalElements:\n" + page.getTotalElements());
		
		if( page.getTotalElements() > 0) {
			for(Blog blog :page.getContent() ) {
				System.out.println(blog);
			}
		}
		//System.out.println("getContent:\n" + page.getContent());
		
		// assertThat(page.getTotalElements()).isEqualTo(2);
	}

}
