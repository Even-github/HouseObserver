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
	List<ProvinceAveragePrice> selectProvinceAveragePriceByCrawlTime(Double time);
	List<CityAveragePrice> selectCityAveragePriceByCrawlTime(Double time);
	Double selectCityAveragePriceByType(@Param(value = "city")String city, 
			@Param(value = "type")String type, @Param(value = "time")Double time);
	List<String> selectCity();
	List<CountyAveragePrice> selectAveragePriceByCityCounty(String city);
}
