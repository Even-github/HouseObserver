package per.even.HouseObserver.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import per.even.HouseObserver.admin.service.HouseService;
import per.even.HouseObserver.common.model.House;
import per.even.HouseObserver.common.model.vo.Page;
import per.even.HouseObserver.common.response.DataListResponse;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;

@RestController
@RequestMapping("/admin/house")
public class HouseController {
	@Autowired
	private HouseService houseService;
	
	@RequestMapping("/getData")
	public DataListResponse<House> getData(Page page, 
			@RequestParam(value = "keyword", required = false)String keyword) {
		List<House> dataList = null;
		if (!StringUtils.isEmpty(keyword)) {
			dataList = houseService.selectByKeyword(page, keyword);
		} else {
			dataList = houseService.selectByPage(page);
		}
		PageInfo<House> pageInfo = new PageInfo<House>(dataList);
		return new DataListResponse<House>(dataList, pageInfo.getPageNum(), pageInfo.getTotal());
	}
	
	@RequestMapping("/deleteData")
	public ResultResponse deleteData(@RequestParam(value = "ids[]")List<String> ids) {
		return new ResultResponse(houseService.deleteByIds(ids));
	}
	
	@RequestMapping("/getById")
	public DataResponse<House> getById(String id) {
		return new DataResponse<House>(houseService.selectById(id));
	}
	
	@RequestMapping("/updateById")
	public ResultResponse updateData(House house) {
		return new ResultResponse(houseService.updateById(house));
	}
	
	@RequestMapping("/insert")
	public ResultResponse insert(House house) {
		return new ResultResponse(houseService.insert(house));
	}
}
