package com.steven.webmagic.app.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * 参考官方的例子，学习如何爬取新浪博客网站的页面
 * 
 * @author Steven
 *
 */
public class SinaBlogProcessorAppDemo implements PageProcessor {

	// 博客列表的URL正式表达式
	public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

	// 博客文章详细内容的正则表达式
	public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

	// 结果保存的路径
	public static final String SAVE_PATH = "F:\\temp\\";

	// 使用文件保存抓取URL，可以在关闭程序并下次启动时，从之前抓取到的URL继续抓取
	// 保存已经下载的URL路径
	public static final String SPIDE_FILE_CACHE_PATH = "F:\\temp\\webmagic_cache\\";

	private Site site = Site.me().setDomain("blog.sina.com.cn").setSleepTime(3000).setRetryTimes(3).setTimeOut(10000);

	public void process(Page page) {
		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
			page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
			// 文章页
		} else {
			page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
			page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
			page.putField("date",
					page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
		}
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new SinaBlogProcessorAppDemo())
				.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
				.setScheduler(new FileCacheQueueScheduler(SPIDE_FILE_CACHE_PATH))
				.addPipeline(new JsonFilePipeline(SAVE_PATH)).run();
	}

}
