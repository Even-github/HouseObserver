package per.even.HouseObserver.utils;

import java.util.Comparator;

import per.even.HouseObserver.common.model.CityPriceStatistics;

public class CityPriceStatisticsRateComparator implements Comparator<CityPriceStatistics>{

	@Override
	public int compare(CityPriceStatistics obj1, CityPriceStatistics obj2) {
		Double obj1WeekGrowthRate = obj1.getWeekGrowthRate();
		Double obj2WeekGrowthRate = obj2.getWeekGrowthRate();
		if(obj1WeekGrowthRate == null) {
			obj1WeekGrowthRate = 0.0d;
		}
		if(obj2WeekGrowthRate  == null) {
			obj2WeekGrowthRate = 0.0d;
		}
		if(obj1WeekGrowthRate - obj2WeekGrowthRate > 0) {
			return 1;
		} else if(obj1WeekGrowthRate - obj2WeekGrowthRate < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
