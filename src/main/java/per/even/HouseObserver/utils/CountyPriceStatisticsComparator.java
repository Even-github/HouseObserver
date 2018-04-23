package per.even.HouseObserver.utils;

import java.util.Comparator;

import per.even.HouseObserver.common.model.CountyPriceStatistics;

public class CountyPriceStatisticsComparator implements Comparator<CountyPriceStatistics>{

	@Override
	public int compare(CountyPriceStatistics arg0, CountyPriceStatistics arg1) {
		Double arg0AveragePrice = arg0.getAveragePrice();
		Double arg1AveragePrice = arg1.getAveragePrice();
		if(arg0AveragePrice == null) {
			arg0AveragePrice = 0.0d;
		}
		if(arg1AveragePrice == null) {
			arg1AveragePrice = 0.0d;
		}
		if(arg0AveragePrice - arg1AveragePrice> 0) {
			return 1;
		} else if(arg0AveragePrice - arg1AveragePrice < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
