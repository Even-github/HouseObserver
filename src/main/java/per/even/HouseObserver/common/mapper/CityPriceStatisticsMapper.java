package per.even.HouseObserver.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import per.even.HouseObserver.common.model.CityPriceStatistics;
import per.even.HouseObserver.common.model.vo.AveragePriceAndTime;
import per.even.HouseObserver.common.model.vo.CityAveragePrice;
import per.even.HouseObserver.common.model.vo.CityBasicPriceInfo;
import per.even.HouseObserver.common.model.vo.CityPriceAndRate;
import per.even.HouseObserver.common.model.vo.CityPriceInfo;
import per.even.HouseObserver.common.model.vo.CityPriceMainInfo;
import per.even.HouseObserver.common.model.vo.CityVote;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface CityPriceStatisticsMapper extends Mapper<CityPriceStatistics>{
	Double selectAveragePriceByCityAndTime(@Param(value = "city")String city, 
			@Param(value = "time")Double time);
	
	int bulkInsert(List<CityPriceStatistics> list);
	
	List<CityPriceInfo> selectCityPriceInfoByPriceRank(
			@Param(value = "rank")Integer topRank, @Param(value = "time")String time);
	
	List<CityPriceAndRate> selectCityPriceAndRateByProvince(String province);
	
	CityPriceMainInfo selectByCity(@Param(value = "city")String city);
	
	List<AveragePriceAndTime> selectAveragePriceAndTimeByCity(
			@Param(value = "city")String city);
	
	List<CityBasicPriceInfo> selectBasicInfoByCitys(
			@Param(value = "city1")String city1, @Param(value = "city2")String city2);

	Integer selectVoteRiseById(String id);

	Integer selectVoteFallById(String id);

	Integer selectVoteSmoothById(String id);

	CityVote selectCityVoteById(String id);
 }
