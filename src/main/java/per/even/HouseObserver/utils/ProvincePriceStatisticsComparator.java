package per.even.HouseObserver.utils;

import java.util.Comparator;

import per.even.HouseObserver.common.model.ProvincePriceStatistics;

public class ProvincePriceStatisticsComparator implements Comparator<ProvincePriceStatistics>{

	@Override
	public int compare(ProvincePriceStatistics obj1, ProvincePriceStatistics obj2) {
		Double obj1AveragePrize = obj1.getAveragePrice();
		Double obj2AveragePrize = obj2.getAveragePrice();
		if (obj1AveragePrize == null) {
			obj1AveragePrize = 0.0d;
		}
		if (obj2AveragePrize == null) {
			obj2AveragePrize = 0.0d;
		}
		
		if (obj1AveragePrize - obj2AveragePrize > 0) {
			return 1;
		} else if (obj1AveragePrize - obj2AveragePrize < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	

}
