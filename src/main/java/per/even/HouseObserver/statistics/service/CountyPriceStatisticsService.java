package per.even.HouseObserver.statistics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.admin.service.HouseService;
import per.even.HouseObserver.common.mapper.CountyPriceStatisticsMapper;
import per.even.HouseObserver.common.model.CityPriceStatistics;
import per.even.HouseObserver.common.model.CountyPriceStatistics;
import per.even.HouseObserver.common.model.vo.CountyAveragePrice;
import per.even.HouseObserver.common.model.vo.CountyPriceInfo;
import per.even.HouseObserver.utils.CountyPriceStatisticsComparator;
import per.even.HouseObserver.utils.NumberFormatter;
import per.even.HouseObserver.utils.TimeUtil;

@Service
public class CountyPriceStatisticsService {
	//默认搜索12天内的统计记录
	private static final int SEARCHTIMERANGE = 12; 
	
	@Autowired
	private CountyPriceStatisticsMapper countyPriceStatisticsMapper;
	@Autowired
	private HouseService houseService;
	
	public boolean statisticsAll() {
		List<String> cityList = houseService.selectCity();
		if(!cityList.isEmpty()) {
			List<CountyPriceStatistics> allStatistics = new ArrayList<>();
			for(String city : cityList) {
				List<CountyPriceStatistics> statisticsList = 
						this.statisticsRank(
								this.statisticsAveragePriceByCity(city));
				allStatistics.addAll(statisticsList);
			}
			allStatistics = 
					this.statisticsWeekGrowthRate(allStatistics);
			if(!allStatistics.isEmpty()) {
				return countyPriceStatisticsMapper.bulkInsert(allStatistics) > 0;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public List<CountyPriceStatistics> statisticsAveragePriceByCity(String city) {
		List<CountyAveragePrice> priceList = 
				houseService.selectAveragePriceByCityCounty(city);
		List<CountyPriceStatistics> statisticsList = new ArrayList<>();
		for(CountyAveragePrice price : priceList) {
			CountyPriceStatistics statistics = new CountyPriceStatistics();
			statistics.setCity(city);
			BeanUtils.copyProperties(price, statistics);
			statistics.setStatisticalTime(
					NumberFormatter.doubleFormat(TimeUtil.getCurrentTimeStamp()));
			statisticsList.add(statistics);
		}
		return statisticsList;
	}
	
	public List<CountyPriceStatistics> statisticsRank(
			List<CountyPriceStatistics> list) {
		if(!list.isEmpty()) {
			list.sort(new CountyPriceStatisticsComparator());
			int rank = 1;
			for(int i = list.size() - 1; i >= 0; i--) {
				list.get(i).setCityRank(rank++);
			}
		}
		return list;
	}
	
	public List<CountyPriceStatistics> statisticsWeekGrowthRate(
			List<CountyPriceStatistics> list) {
		for(CountyPriceStatistics statistics : list) {
			Double targetTime = 
					TimeUtil.getManyDaysAgo(SEARCHTIMERANGE, 
							Double.valueOf(statistics.getStatisticalTime()));
			Double lastWeekAveragePrice = countyPriceStatisticsMapper.selectLastWeekAveragePrice(
					statistics.getCity(), statistics.getCounty(), targetTime);
			if(lastWeekAveragePrice != null) {
				Double weekGrowthRate = (statistics.getAveragePrice() - lastWeekAveragePrice) * 100 / lastWeekAveragePrice;
				statistics.setWeekGrowthRate(weekGrowthRate);
			}
		}
		return list;
	}
	
	public List<CountyPriceInfo> getCountyPriceInfoByCity(String city) {
		return countyPriceStatisticsMapper.selectByCity(city);
	}
}
