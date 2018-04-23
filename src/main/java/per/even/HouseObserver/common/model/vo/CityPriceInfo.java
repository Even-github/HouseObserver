package per.even.HouseObserver.common.model.vo;

public class CityPriceInfo {
	private String city;
	private Double cityAveragePrice;
	private Double cityRate;
	private String statisticalTime; //统计时间
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getCityAveragePrice() {
		return cityAveragePrice;
	}
	public void setCityAveragePrice(Double cityAveragePrice) {
		this.cityAveragePrice = cityAveragePrice;
	}
	public Double getCityRate() {
		return cityRate;
	}
	public void setCityRate(Double cityRate) {
		this.cityRate = cityRate;
	}
	public String getStatisticalTime() {
		return statisticalTime;
	}
	public void setStatisticalTime(String statisticalTime) {
		this.statisticalTime = statisticalTime;
	}
	
}
