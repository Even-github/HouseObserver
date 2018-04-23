package per.even.HouseObserver.common.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class CityPriceStatistics {
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;
	private String city; //城市
	private Double averagePrice; // 平均房价
	private Double newAveragePrice;  //新房平均房价
	private Double usedAveragePrice; //二手房平均房价
	private Double weekGrowthRate; //比上周增长率
	private Integer nationRateRank; //增长率全国城市排名
	private Integer nationPriceRank; //全国房价排名
	private Integer voteRise; //投票上涨数量
	private Integer voteFall; //投票下跌数量
	private Integer voteSmooth; //投票平稳数量
	private String statisticalTime; //统计时间
	
	public CityPriceStatistics() {
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
	public int getNationRateRank() {
		return nationRateRank;
	}
	public void setNationRateRank(int nationRateRank) {
		this.nationRateRank = nationRateRank;
	}
	public int getVoteRise() {
		return voteRise;
	}
	public void setVoteRise(int voteRise) {
		this.voteRise = voteRise;
	}
	public int getVoteFall() {
		return voteFall;
	}
	public void setVoteFall(int voteFall) {
		this.voteFall = voteFall;
	}
	public String getStatisticalTime() {
		return statisticalTime;
	}
	public void setStatisticalTime(String statisticalTime) {
		this.statisticalTime = statisticalTime;
	}

	public Integer getNationPriceRank() {
		return nationPriceRank;
	}

	public void setNationPriceRank(Integer nationPriceRank) {
		this.nationPriceRank = nationPriceRank;
	}

	public Integer getVoteSmooth() {
		return voteSmooth;
	}

	public void setVoteSmooth(Integer voteSmooth) {
		this.voteSmooth = voteSmooth;
	}

	public void setNationRateRank(Integer nationRateRank) {
		this.nationRateRank = nationRateRank;
	}

	public void setVoteRise(Integer voteRise) {
		this.voteRise = voteRise;
	}

	public void setVoteFall(Integer voteFall) {
		this.voteFall = voteFall;
	}
}
