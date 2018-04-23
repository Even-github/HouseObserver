package per.even.HouseObserver.common.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class ProvincePriceStatistics {
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;
	private String province; //省份
	private Double averagePrice; //省平均房价
	private Double weekGrowthRate; //比上周增长率
	private Integer prizeRank; //平均房价排名
	private String statisticalTime; //统计时间
	
	public ProvincePriceStatistics() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
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

	public Integer getPrizeRank() {
		return prizeRank;
	}

	public void setPrizeRank(Integer prizeRank) {
		this.prizeRank = prizeRank;
	}
	
}
