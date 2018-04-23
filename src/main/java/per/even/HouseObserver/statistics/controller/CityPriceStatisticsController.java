package per.even.HouseObserver.statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.even.HouseObserver.common.model.vo.CityNewPriceBean;
import per.even.HouseObserver.common.model.vo.CityPriceInfo;
import per.even.HouseObserver.common.response.BaseResponse;
import per.even.HouseObserver.common.response.DataListResponse;
import per.even.HouseObserver.common.response.DataResponse;
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
}
