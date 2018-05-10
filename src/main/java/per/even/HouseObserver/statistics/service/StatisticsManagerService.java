package per.even.HouseObserver.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.common.model.StatisticsLog;
import per.even.HouseObserver.utils.NumberFormatter;
import per.even.HouseObserver.utils.TimeUtil;

@Service
public class StatisticsManagerService {
	public final static String SUCCESS = "SUCCESS";
	public final static String FAILURE = "FAILURE";
	public final static String RUNNING = "RUNNING";
	
	//运行状态，true表示正在运行，false表示没有运行
	private static boolean state = false;
	
	@Autowired
	private ProvincePriceStatisticsService provincePriceStatisticsService;
	@Autowired
	private CityPriceStatisticsService cityPriceStatisticsService;
	@Autowired
	private CountyPriceStatisticsService countyPriceStatisticsService;
	@Autowired
	private StatisticsLogService statisticsLogService;
	
	/**
	 * 统计省份、城市、区域的所有房价数据，开始时间为上一次的统计时间
	 * @param endTime 结束时间
	 * @return
	 */
	public String statisticsAll(Double endTime) {
		boolean state = this.isRunning(true);
		if (state) {
			return StatisticsManagerService.RUNNING;
		} else {
			//统计的开始时间是上一次的统计时间
			Double beginTime = Double.valueOf(statisticsLogService.getLastTime());
			if (beginTime == null) { //如果是第一次统计，则以0.0作为开始时间
				beginTime = 0.0d;
			}
			StatisticsLog log = new StatisticsLog();
			//记录本次统计的 时间
			log.setStatisticsTime(NumberFormatter.doubleFormat(
					TimeUtil.getCurrentTimeStamp()));
			System.out.println("开始统计...");
			Double time1 = TimeUtil.getCurrentTimeStamp();
			//统计省份房价
			Boolean provinceFlag = provincePriceStatisticsService.statisticsAll(beginTime, endTime);
			Double time2 = TimeUtil.getCurrentTimeStamp();
			System.out.println("省份统计耗时：" + (time2 - time1) + "s");
			//统计城市房价
			Boolean cityFlag = cityPriceStatisticsService.statisticsAll(beginTime, endTime);
			Double time3 = TimeUtil.getCurrentTimeStamp();
			System.out.println("城市统计耗时：" + (time3 - time2) + "s");
			//统计区域房价
			Boolean countyFlag = countyPriceStatisticsService.statisticsAll(beginTime, endTime);
			Double time4 = TimeUtil.getCurrentTimeStamp();
			System.out.println("县/乡统计耗时：" + (time4 - time3) + "s");
			System.out.println("总耗时：" + (time4 - time1) + "s");
			if (provinceFlag && cityFlag && countyFlag) {
				statisticsLogService.insert(log);
				StatisticsManagerService.state = false;
				return StatisticsManagerService.SUCCESS;
			} else {
				return StatisticsManagerService.FAILURE;
			}
		}
	}
	
	/**
	 * 检测统计程序是否正在运行
	 * @param isExecuteStatistics 
	 * 		true为调用本方法是为了在执行统计操作前检测统计程序是否正在运行，如果没有运行，则将运行标志置位true，并开始运行
	 * 		false表示仅仅返回统计程序的运行状态
	 * @return
	 */
	public synchronized boolean isRunning(boolean isExecuteStatistics) {
		if (StatisticsManagerService.state) {
			return true;
		} else {
			if (isExecuteStatistics) {
				StatisticsManagerService.state = true;
			}
			return false;
		}
	}
}
