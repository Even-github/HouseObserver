package per.even.HouseObserver.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import per.even.HouseObserver.admin.service.DataSourceService;
import per.even.HouseObserver.common.model.SpiderDataSource;
import per.even.HouseObserver.common.model.vo.Page;
import per.even.HouseObserver.common.response.DataListResponse;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;
import per.even.HouseObserver.utils.HttpRequest;

@RestController
@RequestMapping("/admin/spider_data_source")
public class DataSourceController {
	@Autowired
	private DataSourceService datasourceService;

	@RequestMapping("/getData")
	public DataListResponse<SpiderDataSource> getData(Page page, 
			@RequestParam(value = "keyword", required = false)String keyword) {
		List<SpiderDataSource> dataList = null;
		if(StringUtils.isEmpty(keyword)) {
			dataList = datasourceService.selectByPage(page);
		} else {
			dataList = datasourceService.selectByKeyword(page, keyword);
		}
		PageInfo<SpiderDataSource> pageInfo = new PageInfo<>(dataList);
		return new DataListResponse<SpiderDataSource>(dataList, pageInfo.getPageNum(), 
				pageInfo.getTotal());
	}
	
	@RequestMapping("/delData")
	public ResultResponse delData(@RequestParam(value = "ids")List<String> ids) {
		return new ResultResponse(datasourceService.deleteByIds(ids));
	}
	
	@RequestMapping("/getById")
	public DataResponse<SpiderDataSource> getById(String id) {
		return new DataResponse<SpiderDataSource>(datasourceService.selectById(id));
	}
	
	@RequestMapping("/updateById")
	public ResultResponse updateData(SpiderDataSource data) {
		return new ResultResponse(datasourceService.updateById(data));
	}
	
	@RequestMapping("/insert")
	public ResultResponse saveData(SpiderDataSource data) {
		return new ResultResponse(datasourceService.insert(data));
	}
}
