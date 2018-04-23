package per.even.HouseObserver.statistics.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.admin.service.HouseService;
import per.even.HouseObserver.common.mapper.CityPriceStatisticsMapper;
import per.even.HouseObserver.common.model.CityPriceStatistics;
import per.even.HouseObserver.common.model.vo.CityAveragePrice;
import per.even.HouseObserver.common.model.vo.CityNewPriceBean;
import per.even.HouseObserver.common.model.vo.CityPriceAndRate;
import per.even.HouseObserver.common.model.vo.CityPriceInfo;
import per.even.HouseObserver.common.model.vo.CityPriceMainInfo;
import per.even.HouseObserver.common.model.vo.PriceChart;
import per.even.HouseObserver.common.model.vo.AveragePriceAndTime;
import per.even.HouseObserver.utils.CityPriceStatisticsPrizeComparator;
import per.even.HouseObserver.utils.CityPriceStatisticsRateComparator;
import per.even.HouseObserver.utils.NumberFormatter;
import per.even.HouseObserver.utils.TimeUtil;

@Service
public class CityPriceStatisticsService {
	//默认搜索12天内的统计记录
	private static final int SEARCHTIMERANGE = 12; 
	@Autowired
	private CityPriceStatisticsMapper cityPriceStatisticsMapper;
	@Autowired
	private HouseService houseService;
	@Autowired
	private StatisticsLogService statisticsLogService;
	@Autowired
	private CountyPriceStatisticsService countyPriceStatisticsService;
	
	public boolean statisticsAll() {
		List<CityPriceStatistics> list =
				this.statisticsNationRateRank(
						this.statisticsWeekGrowthRate(
								this.statisticsUsedAveragePrice(
										this.statisticsNewAveragePrice(
												this.statisticsNationPrizeRank(
														this.statisticsAveragePrice())))));
		if(list != null) {
			return cityPriceStatisticsMapper.bulkInsert(list) > 0;
		} else {
			return false;
		}
	}
	
	/**
	 * 统计平均房价
	 * @return
	 */
	public List<CityPriceStatistics> statisticsAveragePrice() {
		List<CityAveragePrice> list = houseService.selectCityAveragePriceByCrawlTime(
				TimeUtil.getLastWeekTimeStamp());
		List<CityPriceStatistics> statisticsList = new ArrayList<>(); 
		for(CityAveragePrice price : list) {
			CityPriceStatistics statistics = new CityPriceStatistics();
			statistics.setVoteRise(0);
			statistics.setVoteFall(0);
			statistics.setVoteSmooth(0);
			statistics.setStatisticalTime(NumberFormatter.doubleFormat(
					TimeUtil.getCurrentTimeStamp()));
			BeanUtils.copyProperties(price, statistics);
			statisticsList.add(statistics);
		}
		return statisticsList;
	}
	
	/**
	 * 统计新房平均房价
	 * @param list
	 * @return
	 */
	public List<CityPriceStatistics> statisticsNewAveragePrice(
			List<CityPriceStatistics> list) {
		for(CityPriceStatistics statistics : list) {
			Double newAveragePrice = houseService.selectCityAveragePriceByType(
					statistics.getCity(),
					"new",
					TimeUtil.getLastWeekTimeStamp());
			statistics.setNewAveragePrice(newAveragePrice);
		}
		return list;
	}
	
	/**
	 * 统计二手房平均房价
	 * @param list
	 * @return
	 */
	public List<CityPriceStatistics> statisticsUsedAveragePrice(
			List<CityPriceStatistics> list) {
		for(CityPriceStatistics statistics : list) {
			Double usedAveragePrice = houseService.selectCityAveragePriceByType(
					statistics.getCity(),
					"used",
					TimeUtil.getLastWeekTimeStamp());
			statistics.setUsedAveragePrice(usedAveragePrice);
		}
		return list;
	}
	
	/**
	 * 统计比上周增长率
	 * @param list
	 * @return
	 */
	public List<CityPriceStatistics> statisticsWeekGrowthRate(
			List<CityPriceStatistics> list) {
		for(CityPriceStatistics statistics : list) {
			Double targetTime = 
					TimeUtil.getManyDaysAgo(SEARCHTIMERANGE, 
							Double.valueOf(statistics.getStatisticalTime()));
			Double lastWeekAveragePrice = cityPriceStatisticsMapper.selectAveragePriceByCityAndTime(
					statistics.getCity(), targetTime);
			if(lastWeekAveragePrice != null) {
				Double weekGrowthRate = (statistics.getAveragePrice() - lastWeekAveragePrice) * 100 / lastWeekAveragePrice;
				statistics.setWeekGrowthRate(weekGrowthRate);
			}
		}
		return list;
	}
	
	/**
	 * 统计增长率全国排名
	 * @param list
	 * @return
	 */
	public List<CityPriceStatistics> statisticsNationRateRank(
			List<CityPriceStatistics> list) {
		if(list != null) {
			list.sort(new CityPriceStatisticsRateComparator());
			int rank = 1;
			for(int i = list.size() - 1; i >= 0; i--) {
				list.get(i).setNationRateRank(rank++);
			}
		}
		return list;
	}

	/**
	 * 统计平均房价的全国排名
	 * @param list
	 * @return
	 */
	public List<CityPriceStatistics> statisticsNationPrizeRank(
			List<CityPriceStatistics> list) {
		if (list != null) {
			list.sort(new CityPriceStatisticsPrizeComparator());
			int rank = 1;
			for(int i = list.size() - 1; i >= 0; i--) {
				list.get(i).setNationPriceRank(rank++);
			}
		}
		return list;
	}
	
	public List<CityPriceInfo> getCityPriceInfoTop10() {
		Integer topRank = 10; //查询房价排名前10的城市
		String lastTime = statisticsLogService.getLastTime();
		return cityPriceStatisticsMapper.selectCityPriceInfoByPriceRank(topRank, lastTime);
	}
	
	public List<CityPriceAndRate> getCityPriceAndRateByProvince(String province) {
		return cityPriceStatisticsMapper.selectCityPriceAndRateByProvince(province);
	}
	
	public CityPriceMainInfo getCityPriceStatisticsByCity(String city) {
		return cityPriceStatisticsMapper.selectByCity(city);
	}
	
	/**
	 * 获取曲线图数据
	 * @param city
	 * @return
	 */
	public PriceChart getCityPriceChart(String city) {
		List<AveragePriceAndTime> list = 
				cityPriceStatisticsMapper.selectAveragePriceAndTimeByCity(city);
		PriceChart chart = new PriceChart();
		List<String> timeList = new ArrayList<String>();
		List<Double> priceList = new ArrayList<Double>();
		for (AveragePriceAndTime item : list) {
			timeList.add(item.getStatisticalTime());
			priceList.add(item.getAveragePrice());
		}
		chart.setTimeList(timeList);
		chart.setPriceList(priceList);
		return chart;
	}
	
	public CityNewPriceBean getCityNewPriceByCity(String city) {
		CityNewPriceBean bean = new CityNewPriceBean();
		bean.setCityPriceMainInfo(this.getCityPriceStatisticsByCity(city));
		bean.setCityPriceChart(this.getCityPriceChart(city));
		bean.setCountyPriceInfoList(countyPriceStatisticsService.getCountyPriceInfoByCity(city));
		return bean;
	}
}
