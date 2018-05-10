package per.even.HouseObserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import per.even.HouseObserver.common.model.CountyPriceStatistics;
import per.even.HouseObserver.common.model.StatisticsLog;
import per.even.HouseObserver.statistics.service.CityPriceStatisticsService;
import per.even.HouseObserver.statistics.service.CountyPriceStatisticsService;
import per.even.HouseObserver.statistics.service.ProvincePriceStatisticsService;
import per.even.HouseObserver.statistics.service.StatisticsLogService;
import per.even.HouseObserver.utils.NumberFormatter;
import per.even.HouseObserver.utils.TimeUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseObserverApplicationTests {
	@Autowired
	private ProvincePriceStatisticsService provincePriceStatisticsService;
	@Autowired
	private CityPriceStatisticsService cityPriceStatisticsService;
	@Autowired
	private CountyPriceStatisticsService countyPriceStatisticsService;
	@Autowired
	private StatisticsLogService statisticsLogService;
	
	@Test
	public void contextLoads() {
		Double lastTime = Double.valueOf(statisticsLogService.getLastTime());
		System.out.println("开始统计...");
//		StatisticsLog log = new StatisticsLog();
		String currentTimeStamp = NumberFormatter.doubleFormat(TimeUtil.getCurrentTimeStamp());
//		log.setStatisticsTime(currentTimeStamp);
//		statisticsLogService.insert(log);
//		Double time1 = TimeUtil.getCurrentTimeStamp();
//		System.out.println(
//				"省份统计：" + provincePriceStatisticsService.statisticsAll());
//		Double time2 = TimeUtil.getCurrentTimeStamp();
//		System.out.println("省份统计耗时：" + (time2 - time1) + "s");
//		System.out.println(
//				"城市统计：" + cityPriceStatisticsService.statisticsAll());
		Double time3 = TimeUtil.getCurrentTimeStamp();
//		System.out.println("城市统计耗时：" + (time3 - time2) + "s");
		System.out.println(
				"区域统计：" + countyPriceStatisticsService.statisticsAll(1525010957.744, 
						Double.valueOf(currentTimeStamp)));
		Double time4 = TimeUtil.getCurrentTimeStamp();
		System.out.println("区域统计耗时：" + (time4 - time3) + "s");
//		System.out.println("总耗时：" + (time4 - time1) + "s");
	}
}
