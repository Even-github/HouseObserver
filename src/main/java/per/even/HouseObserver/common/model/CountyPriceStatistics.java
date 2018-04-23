package per.even.HouseObserver.common.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class CountyPriceStatistics {
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;
	private String city; //城市
	private String county; // 区/县
	private Double averagePrice; //省平均房价
	private Double weekGrowthRate; //比上周增长率
	private Integer cityRank; //区域均价排名
	private String statisticalTime; //统计时间
	
	public CountyPriceStatistics() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

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
	public Integer getCityRank() {
		return cityRank;
	}
	public void setCityRank(Integer cityRank) {
		this.cityRank = cityRank;
	}
	public String getStatisticalTime() {
		return statisticalTime;
	}
	public void setStatisticalTime(String statisticalTime) {
		this.statisticalTime = statisticalTime;
	}
	
}
