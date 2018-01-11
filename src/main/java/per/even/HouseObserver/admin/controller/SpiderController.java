package per.even.HouseObserver.admin.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.even.HouseObserver.admin.service.SpiderService;
import per.even.HouseObserver.common.model.vo.DataSourceInfo;
import per.even.HouseObserver.common.response.DataListResponse;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;

@RestController
@RequestMapping("/admin/spider")
public class SpiderController {
	@Autowired
	private SpiderService spiderService;
	
	@RequestMapping("/getDataSourceInfo")
	public DataResponse<List<DataSourceInfo>> getDataSourceInfo() {
		return new DataResponse<List<DataSourceInfo>>(spiderService.getDataSourceInfo());
	}
	
	@RequestMapping("/scheduleBySpiderName")
	public DataResponse<Map<String, String>> runDataSourceSpider(String spiderName) {
		return new DataResponse<>(spiderService.scheduleBySpiderName(spiderName));
	}
	
	@RequestMapping("/scheduleByDataSourceId")
	public DataResponse<Map<String, String>> runSpiderByDataSourceId(String id) {
		return new DataResponse<>(spiderService.scheduleByDataSourceId(id));
	}
	
	@RequestMapping("/listjobs")
	public DataResponse<Map<String, Object>> listJobs() {
		return new DataResponse<>(spiderService.listjobs());
	}
	
	@RequestMapping("/cancelJob")
	public DataResponse<Map<String, String>> cancelJob(String id) {
		return new DataResponse<>(spiderService.cancel(id));
	}
}
