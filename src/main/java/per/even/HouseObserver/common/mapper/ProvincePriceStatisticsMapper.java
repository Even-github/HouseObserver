package per.even.HouseObserver.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import per.even.HouseObserver.common.model.ProvincePriceStatistics;
import per.even.HouseObserver.common.model.vo.ProvinceAveragePrice;
import per.even.HouseObserver.common.model.vo.AveragePriceAndTime;
import per.even.HouseObserver.common.model.vo.ProvinceNewPriceInfo;
import per.even.HouseObserver.common.model.vo.ProvincePriceInfo;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface ProvincePriceStatisticsMapper extends Mapper<ProvincePriceStatistics> {
	Double selectAveragePriceByProvinceAndTime(@Param(value = "province")String province, 
			@Param(value = "time")Double time);
	
	int bulkInsert(List<ProvincePriceStatistics> list);
	
	List<ProvincePriceInfo> selectAveragePriceByTime(
			@Param(value = "time")String time);
	
	ProvinceNewPriceInfo getProvinceNewPriceInfoByProvince(
			@Param(value = "province")String province);
	
	List<AveragePriceAndTime> selectAveragePriceAndTimeByProvince(
			@Param(value = "province")String province);
	
	List<ProvincePriceInfo> selectNewAveragePrice();
	
}
