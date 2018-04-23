package per.even.HouseObserver.common.model.vo;

import java.util.List;

public class PriceChart {
	private List<String> timeList;
	private List<Double> priceList;
	public List<String> getTimeList() {
		return timeList;
	}
	public void setTimeList(List<String> timeList) {
		this.timeList = timeList;
	}
	public List<Double> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<Double> priceList) {
		this.priceList = priceList;
	}

}
