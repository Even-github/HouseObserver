package per.even.HouseObserver.utils;

import java.util.Comparator;

import per.even.HouseObserver.common.model.CityPriceStatistics;

public class CityPriceStatisticsPrizeComparator implements Comparator<CityPriceStatistics> {

	@Override
	public int compare(CityPriceStatistics obj1, CityPriceStatistics obj2) {
		Double obj1AveragePrice = obj1.getAveragePrice();
		Double obj2AveragePrice = obj2.getAveragePrice();
		if(obj1AveragePrice == null) {
			obj1AveragePrice = 0.0d;
		}
		if(obj2AveragePrice  == null) {
			obj2AveragePrice = 0.0d;
		}
		if(obj1AveragePrice - obj2AveragePrice > 0) {
			return 1;
		} else if(obj1AveragePrice - obj2AveragePrice < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
