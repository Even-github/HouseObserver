package per.even.HouseObserver.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import per.even.HouseObserver.common.model.House;
import per.even.HouseObserver.common.model.vo.CityAveragePrice;
import per.even.HouseObserver.common.model.vo.CountyAveragePrice;
import per.even.HouseObserver.common.model.vo.ProvinceAveragePrice;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface HouseMapper extends Mapper<House>{
	List<House> selectByKeyword(String keyword);
	
	int bulkDeleteById(List<String> id);
	
	List<ProvinceAveragePrice> selectProvinceAveragePriceByCrawlTime(
			@Param(value = "beginTime")Double beginTime, 
			@Param(value = "endTime")Double endTime);
	
	List<CityAveragePrice> selectCityAveragePriceByCrawlTime(
			@Param(value = "beginTime")Double beginTime, 
			@Param(value = "endTime")Double endTime);
	
	Double selectCityAveragePriceByType(@Param(value = "city")String city, 
			@Param(value = "type")String type, 
			@Param(value = "beginTime")Double beginTime,
			@Param(value = "endTime")Double endTime);
	
	List<String> selectCityByCrawlTime(
			@Param(value = "beginTime")Double beginTime, 
			@Param(value = "endTime")Double endTime);
	
	List<CountyAveragePrice> selectAveragePriceByCityCounty(
			@Param(value = "city")String city, 
			@Param(value = "beginTime")Double beginTime, 
			@Param(value = "endTime")Double endTime);
}
