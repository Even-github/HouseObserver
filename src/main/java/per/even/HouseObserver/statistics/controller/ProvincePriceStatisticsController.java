package per.even.HouseObserver.statistics.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.even.HouseObserver.common.model.vo.ProvinceMapTableData;
import per.even.HouseObserver.common.model.vo.ProvinceNewPriceBean;
import per.even.HouseObserver.common.model.vo.ProvinceNewPriceInfo;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.statistics.service.ProvincePriceStatisticsService;

@RestController
@RequestMapping("/province")
public class ProvincePriceStatisticsController {
	@Autowired
	private ProvincePriceStatisticsService service;
	
	@RequestMapping("/getAveragePrice")
	public DataResponse<ProvinceMapTableData> getProvinceAveragePrice() {
		return new DataResponse<ProvinceMapTableData>(service.getProvincePriceMap());
	}
	
	@RequestMapping("/getProvincePriceInfo")
	public DataResponse<ProvinceNewPriceBean> getProvinceNewPriceByProvince(String province) {
		if (!StringUtils.isEmpty(province)) {
			return new DataResponse<ProvinceNewPriceBean>(
					service.getProvinceNewPriceByProvince(province));
		} else {
			return new DataResponse<>();
		}
	}
}
