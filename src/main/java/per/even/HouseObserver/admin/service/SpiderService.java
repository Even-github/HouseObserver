package per.even.HouseObserver.admin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import per.even.HouseObserver.common.model.SpiderDataSource;
import per.even.HouseObserver.common.model.SpiderDataSourceRoot;
import per.even.HouseObserver.common.model.vo.DataSourceInfo;
import per.even.HouseObserver.utils.HttpRequest;

@Service
public class SpiderService {
	private static final String SPIDER_HOST = "http://localhost:6800/";
	private static final String SPIDER_PROJECT = "HouseCrawler";
	private static final String DAEMONSTATUS = "daemonstatus.json";
	private static final String SCHEDULE = "schedule.json";
	private static final String CANCEL = "cancel.json";
	private static final String LISTSPIDERS = "listspiders.json";
	private static final String LISTJOBS = "listjobs.json";
	private static final String AnjukeNewHouseSpider = "AnjukeNewHouseSpider";
	private static final String AnjukeUsedHouseSpider = "AnjukeUsedHouseSpider";
	private static final String LianjiaNewHouseSpider = "LianjiaNewHouseSpider";
	private static final String LianjiaUsedHouseSpider = "LianjiaUsedHouseSpider";
	
	@Autowired
	private DataSourceService dataSourceService;
	@Autowired
	private DataSourceRootService dataSourceRootService;
	
	/**
	 * 获取爬虫数据源列表
	 * @return
	 */
	public List<DataSourceInfo> getDataSourceInfo() {
		List<DataSourceInfo> dataList = new ArrayList<>();
		List<SpiderDataSourceRoot> dataSourceRootList = dataSourceRootService.selectAll();
		for (SpiderDataSourceRoot s : dataSourceRootList) {
			DataSourceInfo info = new DataSourceInfo();
			info.setName(s.getName());
			info.setUrl(s.getUrl());
			info.setSpider(s.getSpider());
			dataList.add(info);
		}
		for (DataSourceInfo info : dataList) {
			info.setCount(dataSourceService.countBySourceName(info.getName()));
		}
		return dataList;
	}
	
	/**
	 * 调度启动一个爬虫
	 * @param 直接通过爬虫名称调度爬虫，无参数
	 * {"status": "ok", "jobid": "6487ec79947edab326d6db28a2d86511e8247444"}
	 * @return
	 */
	public Map<String, String> scheduleBySpiderName(String spiderName) {
		String url = SPIDER_HOST + SCHEDULE;
		String param = "project=HouseCrawler&spider=" + spiderName;
		String result = HttpRequest.sendPost(url, param);
		ObjectMapper mapper = new ObjectMapper();
		Map map = null;
		try {
			map = mapper.readValue(result, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *  调度启动一个爬虫
	 * @param dataSourceId 通过爬虫数据源id间接调度爬虫
	 * @return
	 */
	public Map<String, String> scheduleByDataSourceId(String dataSourceId) {
		String url = SPIDER_HOST + SCHEDULE;
		SpiderDataSource source = dataSourceService.selectById(dataSourceId);
		Map map = null;
		if(source != null) {
			String spider = "";
			if ("安居客".equals(source.getSource_name())) {
				if ("new".equals(source.getType())) {
					spider = SpiderService.AnjukeNewHouseSpider;
				} else {
					spider = SpiderService.AnjukeUsedHouseSpider;
				}
			} else if ("链家网".equals(source.getSource_name())) {
				if ("new".equals(source.getType())) {
					spider = SpiderService.LianjiaNewHouseSpider;
				} else {
					spider = SpiderService.LianjiaUsedHouseSpider;
				}
			} else {}
			if (spider != "") {
				String param = "project=HouseCrawler&spider=" + spider +
						"&city=" + source.getCity() + "&county=" + source.getCounty() +
						"&url=" + source.getUrl();
				String result = HttpRequest.sendPost(url, param);
				ObjectMapper mapper = new ObjectMapper();
				try {
					map = mapper.readValue(result, Map.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	/**
	 * 取消一个爬虫作业
	 * {"status": "ok", "prevstate": "running"}
	 * @param job
	 * @return
	 */
	public Map<String, String> cancel(String job) {
		String url = SPIDER_HOST + CANCEL;
		String param = "project=HouseCrawler&job=" + job;
		String result = HttpRequest.sendPost(url, param);
		Map map = null;
		if (!StringUtils.isEmpty(result)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				map = mapper.readValue(result, Map.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
//	/**
//	 * 输出所有可用的爬虫
//	 * {"status": "ok", "spiders": ["spider1", "spider2", "spider3"]}
//	 * @return
//	 */
//	public Object listspiders() {
//		
//	}
	
	/**
	 * 输出所有爬虫作业
	 * @return
	 */
	public Map<String, Object> listjobs() {
		String url = SPIDER_HOST + LISTJOBS;
		String param = "project=HouseCrawler";
		String result = HttpRequest.sendGet(url, param);
		Map map = null;
		if(!StringUtils.isEmpty(result)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				map = mapper.readValue(result, Map.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
