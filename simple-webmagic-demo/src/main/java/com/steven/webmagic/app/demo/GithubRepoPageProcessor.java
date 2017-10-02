package com.steven.webmagic.app.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 官方自带的例子<br>
 * Github下载爬虫，用于爬取Github页面
 * 
 * @author Steven
 *
 */
public class GithubRepoPageProcessor implements PageProcessor {

	/**
	 * 抓取网站的相关配置，包括编码、抓取间隔、重试次数等：重试次数为3次，抓取间隔为一秒
	 */
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

	public void process(Page page) {
		// page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all()
		// 用于获取所有满足"(https://github\\.com/[\\w\\-]+/[\\w\\-]+))"这个正则表达式的链接，
		// page.addTargetRequests()则将这些链接加入到待抓取的队列中去。
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
		page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
		page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		// 查找所有class属性为'entry-title public'的h1元素，并找到他的strong子节点的a子节点，并提取a节点的文本信息。
		page.putField("name", page.getHtml().xpath("//h1[@class='public']/strong/a/text()").toString());
		if (page.getResultItems().get("name") == null) {
			// skip this page
			page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft")
				.addPipeline(new JsonFilePipeline("F:\\temp\\")).thread(5).run();
	}

}
