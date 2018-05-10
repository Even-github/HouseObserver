package per.even.HouseObserver.common.model.vo;

public class CityBasicPriceInfo {
	private String city; //城市
	private Double averagePrice; // 平均房价
	private Double newAveragePrice;  //新房平均房价
	private Double usedAveragePrice; //二手房平均房价
	private Double weekGrowthRate; //比上周增长率
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public Double getNewAveragePrice() {
		return newAveragePrice;
	}
	public void setNewAveragePrice(Double newAveragePrice) {
		this.newAveragePrice = newAveragePrice;
	}
	public Double getUsedAveragePrice() {
		return usedAveragePrice;
	}
	public void setUsedAveragePrice(Double usedAveragePrice) {
		this.usedAveragePrice = usedAveragePrice;
	}
	public Double getWeekGrowthRate() {
		return weekGrowthRate;
	}
	public void setWeekGrowthRate(Double weekGrowthRate) {
		this.weekGrowthRate = weekGrowthRate;
	}
	
}
