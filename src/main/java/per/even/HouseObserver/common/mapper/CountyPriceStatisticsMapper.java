package per.even.HouseObserver.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import per.even.HouseObserver.common.model.CountyPriceStatistics;
import per.even.HouseObserver.common.model.vo.CountyPriceInfo;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface CountyPriceStatisticsMapper extends Mapper<CountyPriceStatistics>{
	Double selectLastWeekAveragePrice(
			@Param("city")String city, @Param("county")String county, 
			@Param("time")Double time);
	int bulkInsert(List<CountyPriceStatistics> list);
	List<CountyPriceInfo> selectByCity(String city);
}
