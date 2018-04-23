package per.even.HouseObserver.common.model.vo;

public class CountyPriceInfo {
	private String county; // 区/县
	private Double averagePrice; //省平均房价
	private Double weekGrowthRate; //比上周增长率
	private String statisticalTime; //统计时间
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
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
