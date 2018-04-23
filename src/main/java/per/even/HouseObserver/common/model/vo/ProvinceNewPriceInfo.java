package per.even.HouseObserver.common.model.vo;

public class ProvinceNewPriceInfo extends ProvinceAveragePrice{
	private Double weekGrowthRate;
	private String statisticalTime;

	public Double getWeekGrowthRate() {
		return weekGrowthRate;
	}
	public void setWeekGrowthRate(Double weekGrowthRate) {
		this.weekGrowthRate = weekGrowthRate;
	}
	public String getStatisticalTime() {
		return statisticalTime;
	}
	public void setStatisticalTime(String statisticalTime) {
		this.statisticalTime = statisticalTime;
	}
}
