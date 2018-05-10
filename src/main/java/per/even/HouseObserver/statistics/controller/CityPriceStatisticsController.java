package per.even.HouseObserver.statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Data;

import per.even.HouseObserver.common.model.vo.CityBasicPriceInfo;
import per.even.HouseObserver.common.model.vo.CityContrast;
import per.even.HouseObserver.common.model.vo.CityNewPriceBean;
import per.even.HouseObserver.common.model.vo.CityPriceInfo;
import per.even.HouseObserver.common.model.vo.CityVote;
import per.even.HouseObserver.common.response.BaseResponse;
import per.even.HouseObserver.common.response.DataListResponse;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;
import per.even.HouseObserver.statistics.service.CityPriceStatisticsService;

@RestController
@RequestMapping("/city")
public class CityPriceStatisticsController {
	@Autowired
	private CityPriceStatisticsService cityPriceStatisticsService;
	
	@RequestMapping("/getCityPriceInfo")
	public DataListResponse<CityPriceInfo> getCityPriceInfo() {
		return new DataListResponse<CityPriceInfo>(
				cityPriceStatisticsService.getCityPriceInfoTop10());
	}
	
	@RequestMapping("/getCityNewPrice")
	public DataResponse<CityNewPriceBean> getCityNewPriceByCity(String city) {
		if (!StringUtils.isEmpty(city)) {
			return new DataResponse<CityNewPriceBean>(
					cityPriceStatisticsService.getCityNewPriceByCity(city));
		} else {
			return new DataResponse<>();
		}
	}
	
	@RequestMapping("/getCityContrast")
	public DataResponse<CityContrast> getCityContrast(String city1, String city2) {
		DataResponse<CityContrast> response = new DataResponse<>();
		response.setCode("200");
		if (!StringUtils.isEmpty(city1) && !StringUtils.isEmpty(city2)) {
			CityContrast cityContrast = 
					cityPriceStatisticsService.getCityContrast(city1, city2);
			if (cityContrast.getCityInfo2() != null) {
				response.setData(cityContrast);
				response.setMessage("ok");
			} else {
				CityBasicPriceInfo cityInfo1 = cityContrast.getCityInfo1();
				if (cityInfo1 != null) { //存在其中一个城市的信息
					if (city1.equals(cityInfo1.getCity())) {
						response.setMessage("无" + city2 + "的相关信息！");
					} else {
						response.setMessage("无" + city1 + "的相关信息！");
					}
				} else { //两个城市的信息都不存在
					response.setMessage("无" + city1 + "和" + city2 + "的相关信息！");
				}
			}
		} else {
			response.setMessage("请输入城市名称！");;
		}
		return response;
	}
	
	@RequestMapping("/submitVote")
	public ResultResponse submitVote(String id, Integer selected) {
		if (id != null && selected != null) {
			return new ResultResponse(
					cityPriceStatisticsService.submitVote(id, selected));
		} else {
			return new ResultResponse(false, "参数错误！");
		}
	}
	
	@RequestMapping("/getCityVote")
	public DataResponse<CityVote> getCityVote(String id) {
		if (id != null) {
			return new DataResponse<CityVote>(
					cityPriceStatisticsService.getCityVoteById(id));
		} else {
			return new DataResponse<CityVote>();
		}
	}
}
