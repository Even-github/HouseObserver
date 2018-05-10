package per.even.HouseObserver.statistics.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.admin.service.HouseService;
import per.even.HouseObserver.common.mapper.ProvincePriceStatisticsMapper;
import per.even.HouseObserver.common.mapper.StatisticsLogMapper;
import per.even.HouseObserver.common.model.ProvincePriceStatistics;
import per.even.HouseObserver.common.model.StatisticsLog;
import per.even.HouseObserver.common.model.vo.ProvinceAveragePrice;
import per.even.HouseObserver.common.model.vo.AveragePriceAndTime;
import per.even.HouseObserver.common.model.vo.ProvinceMapTableData;
import per.even.HouseObserver.common.model.vo.ProvinceNewPriceBean;
import per.even.HouseObserver.common.model.vo.PriceChart;
import per.even.HouseObserver.common.model.vo.ProvinceNewPriceInfo;
import per.even.HouseObserver.common.model.vo.ProvincePriceInfo;
import per.even.HouseObserver.utils.NumberFormatter;
import per.even.HouseObserver.utils.ProvincePriceStatisticsComparator;
import per.even.HouseObserver.utils.TimeUtil;

@Service
public class ProvincePriceStatisticsService {
	//默认搜索12天内的统计记录
//	private static final int SEARCHTIMERANGE = 12; 
	
	@Autowired
	private ProvincePriceStatisticsMapper provincePriceMapper;
	@Autowired
	private HouseService houseService;
	@Autowired
	private StatisticsLogService statisticsLogService;
	@Autowired
	private CityPriceStatisticsService cityPriceStatisticsService;
	
	public boolean statisticsAll(Double beginTime, Double endTime) {
		List<ProvincePriceStatistics> list =
				this.statisticsPrizeRank(
						this.statisticsWeekGrowthRate(
								this.statisticsAveragePrice(beginTime, endTime), beginTime));
				
		if(!list.isEmpty()) {
			return provincePriceMapper.bulkInsert(list) > 0;
		} else {
			return false;
		}
	}
	
	/**
	 * 统计均价
	 * @return
	 */
	public List<ProvincePriceStatistics> statisticsAveragePrice(
			Double beginTime, Double endTime) {
		List<ProvinceAveragePrice> list = 
				houseService.selectProvinceAveragePriceByCrawlTime(beginTime, endTime);
		List<ProvincePriceStatistics> statisticsList = new ArrayList<>();
		for(ProvinceAveragePrice price : list) {
			ProvincePriceStatistics statistics = new ProvincePriceStatistics();
			BeanUtils.copyProperties(price, statistics);
			String currentTimeStamp = NumberFormatter.doubleFormat(TimeUtil.getCurrentTimeStamp());
			statistics.setStatisticalTime(currentTimeStamp);
			statisticsList.add(statistics);
		}
		return statisticsList;
	}
	
	/**
	 * 统计比上周增长率
	 * @return
	 */
	public List<ProvincePriceStatistics> statisticsWeekGrowthRate(
			List<ProvincePriceStatistics> list, Double lastStatisticsTime) {
		for(ProvincePriceStatistics pps : list) {
//			Double targeTime = 
//					TimeUtil.getManyDaysAgo(SEARCHTIMERANGE, Double.valueOf(pps.getStatisticalTime()));
			Double lastWeekAveragePrice = 
					provincePriceMapper.selectAveragePriceByProvinceAndTime(pps.getProvince(),
							lastStatisticsTime);
			if(lastWeekAveragePrice != null) {
				pps.setWeekGrowthRate(
						(pps.getAveragePrice() - lastWeekAveragePrice) * 100 / lastWeekAveragePrice);
			}
		}
		return list;
	}
	
	/**
	 * 统计省份平均房价全国排名
	 * @param list
	 * @return
	 */
	public List<ProvincePriceStatistics> statisticsPrizeRank(
			List<ProvincePriceStatistics> list) {
		if (list != null) {
			list.sort(new ProvincePriceStatisticsComparator());
			int rank = 1;
			for (int i = list.size() - 1; i >= 0; i--) {
				list.get(i).setPrizeRank(rank++);
			}
		}
		return list;
	}
	
	/**
	 * 获取全国房价页面中地图和省份表格的数据
	 * @return
	 */
	public ProvinceMapTableData getProvincePriceMap() {
		List<ProvincePriceInfo> list = provincePriceMapper
				.selectNewAveragePrice();
		ProvinceMapTableData mapTable = new ProvinceMapTableData();
		mapTable.setProvincePriceInfo(list);
		Map<String, Double> map = new HashMap<>();
		for(ProvincePriceInfo price : list) {
			map.put(price.getProvince(), price.getProvinceAveragePrice());
		}
		mapTable.setHousingMapData(map);
		return mapTable;
	}
	
	/**
	 * 获取省份最新房价信息
	 * @return
	 */
	public ProvinceNewPriceInfo getProvinceNewPriceInfo(String province) {
		return provincePriceMapper.getProvinceNewPriceInfoByProvince(province);
	}
	
	/**
	 * 获取曲线图数据
	 * @param province
	 * @return
	 */
	public PriceChart getProvinceNewPriceChart(String province) {
		List<AveragePriceAndTime> list = 
				provincePriceMapper.selectAveragePriceAndTimeByProvince(province);
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
	
	/**
	 * 获取省份房价页面的数据
	 * @param province
	 * @return
	 */
	public ProvinceNewPriceBean getProvinceNewPriceByProvince(String province) {
		ProvinceNewPriceBean bean = new ProvinceNewPriceBean();
		bean.setProvinceNewPriceInfo(this.getProvinceNewPriceInfo(province));
		bean.setProvinceNewPriceChart(this.getProvinceNewPriceChart(province));
		bean.setCityPriceInfoList(cityPriceStatisticsService.getCityPriceAndRateByProvince(province));
		return bean;
	}
}
