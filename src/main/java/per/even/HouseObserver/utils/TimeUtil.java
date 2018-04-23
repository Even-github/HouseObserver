package per.even.HouseObserver.utils;

public class TimeUtil {
	//一天86400秒
	public static final Double DAYTIMESTAMP = 86400.000;
	//一周604800秒
	public static final Double WEEKTIMESTAMP = 604800.000;
	
	public static Double getCurrentTimeStamp() {
		return (double) (System.currentTimeMillis() / 1000.0d);
	}
	
	public static Double getLastWeekTimeStamp() {
		return getCurrentTimeStamp() - WEEKTIMESTAMP; 
	}
	
	/**
	 * 获取指定时间点指定天数之前的时间戳
	 * @param day 指定天数
	 * @param baseTime 指定时间
	 * @return
	 */
	public static Double getManyDaysAgo(int day, Double baseTime) {
		return baseTime - (DAYTIMESTAMP * day);
	}
}
