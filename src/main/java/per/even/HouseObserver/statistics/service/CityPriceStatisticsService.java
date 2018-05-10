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
import per.even.HouseObserver.common.model.vo.CityBasicPriceInfo;
import per.even.HouseObserver.common.model.vo.CityContrast;
import per.even.HouseObserver.common.model.vo.CityNewPriceBean;
import per.even.HouseObserver.common.model.vo.CityPriceAndRate;
import per.even.HouseObserver.common.model.vo.CityPriceInfo;
import per.even.HouseObserver.common.model.vo.CityPriceMainInfo;
import per.even.HouseObserver.common.model.vo.CityVote;
import per.even.HouseObserver.common.model.vo.PriceChart;
import per.even.HouseObserver.common.model.vo.AveragePriceAndTime;
import per.even.HouseObserver.utils.CityPriceStatisticsPrizeComparator;
import per.even.HouseObserver.utils.CityPriceStatisticsRateComparator;
import per.even.HouseObserver.utils.NumberFormatter;
import per.even.HouseObserver.utils.TimeUtil;

@Service
public class CityPriceStatisticsService {
	//默认搜索12天内的统计记录
//	private static final int SEARCHTIMERANGE = 12; 
	
	//房价预测上涨、下跌、平稳的编号分别为0，1，2
	public static final int VOTERISENUM = 0;
	public static final int VOTEFALLNUM = 1;
	public static final int VOTESMOOTHNUM = 2;
	
	@Autowired
	private CityPriceStatisticsMapper cityPriceStatisticsMapper;
	@Autowired
	private HouseService houseService;
	@Autowired
	private StatisticsLogService statisticsLogService;
	@Autowired
	private CountyPriceStatisticsService countyPriceStatisticsService;
	
	/**
	 * 统计城市房价的所有数据
	 * @return
	 */
	public boolean statisticsAll(Double beginTime, Double endTime) {
		List<CityPriceStatistics> list =
				this.statisticsNationRateRank(
						this.statisticsWeekGrowthRate(
								this.statisticsUsedAveragePrice(
										this.statisticsNewAveragePrice(
												this.statisticsNationPrizeRank(
														this.statisticsAveragePrice(beginTime, endTime)), 
															beginTime, endTime), beginTime, endTime), beginTime));
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
	public List<CityPriceStatistics> statisticsAveragePrice(Double beginTime, Double endTime) {
		List<CityAveragePrice> list = houseService.selectCityAveragePriceByCrawlTime(
				beginTime, endTime);
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
			List<CityPriceStatistics> list, Double beginTime, Double endTime) {
		for(CityPriceStatistics statistics : list) {
			Double newAveragePrice = houseService.selectCityAveragePriceByType(
					statistics.getCity(), "new", beginTime, endTime);
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
			List<CityPriceStatistics> list, Double beginTime, Double endTime) {
		for(CityPriceStatistics statistics : list) {
			Double usedAveragePrice = houseService.selectCityAveragePriceByType(
					statistics.getCity(), "used", beginTime, endTime);
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
			List<CityPriceStatistics> list, Double lastStatisticsTime) {
		for(CityPriceStatistics statistics : list) {
//			Double targetTime = 
//					TimeUtil.getManyDaysAgo(SEARCHTIMERANGE, 
//							Double.valueOf(statistics.getStatisticalTime()));
			Double lastWeekAveragePrice = cityPriceStatisticsMapper.selectAveragePriceByCityAndTime(
					statistics.getCity(), lastStatisticsTime);
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

	/**
	 * 获取两个城市的房价对比数据
	 * @param city1
	 * @param city2
	 * @return
	 */
	public CityContrast getCityContrast(String city1, String city2) {
		CityContrast cityContrast = new CityContrast();
		List<CityBasicPriceInfo> list = 
				cityPriceStatisticsMapper.selectBasicInfoByCitys(city1, city2);
		if(list.size() == 2) {
			cityContrast.setCityInfo1(list.get(0));
			cityContrast.setCityInfo2(list.get(1));
		} else if (list.size() == 1) {
			cityContrast.setCityInfo1(list.get(0));
		}
		return cityContrast;
	}
	
	/**
	 * 房价预测投票
	 * @param id 编号
	 * @param selected 
	 * @return
	 */
	public boolean submitVote(String id, int selected) {
		CityPriceStatistics record = new CityPriceStatistics();
		record.setId(id);
		if (selected == CityPriceStatisticsService.VOTERISENUM) {
			Integer oldVoteCount = cityPriceStatisticsMapper.selectVoteRiseById(id);
			if (oldVoteCount != null) {
				record.setVoteRise(++oldVoteCount);
			} else {
				return false;
			}
		} else if (selected == CityPriceStatisticsService.VOTEFALLNUM) {
			Integer oldVoteCount = cityPriceStatisticsMapper.selectVoteFallById(id);
			if (oldVoteCount != null) {
				record.setVoteFall(++oldVoteCount);
			} else {
				return false;
			}
		} else {
			Integer oldVoteCount = cityPriceStatisticsMapper.selectVoteSmoothById(id);
			if (oldVoteCount != null) {
				record.setVoteSmooth(++oldVoteCount);
			} else {
				return false;
			}
		}
		return cityPriceStatisticsMapper.updateByPrimaryKeySelective(record) > 0;
	}

	public CityVote getCityVoteById(String id) {
		return cityPriceStatisticsMapper.selectCityVoteById(id);
	}
}
