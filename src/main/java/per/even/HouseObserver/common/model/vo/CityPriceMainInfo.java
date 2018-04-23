package per.even.HouseObserver.common.model.vo;

public class CityPriceMainInfo {
	private String city; //城市
	private Double averagePrice; // 平均房价
	private Double newAveragePrice;  //新房平均房价
	private Double usedAveragePrice; //二手房平均房价
	private Double weekGrowthRate; //比上周增长率
	private Integer voteRise; //投票上涨数量
	private Integer voteFall; //投票下跌数量
	private Integer voteSmooth; //投票平稳数量
	private String statisticalTime; //统计时间
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
	public Integer getVoteRise() {
		return voteRise;
	}
	public void setVoteRise(Integer voteRise) {
		this.voteRise = voteRise;
	}
	public Integer getVoteFall() {
		return voteFall;
	}
	public void setVoteFall(Integer voteFall) {
		this.voteFall = voteFall;
	}
	public Integer getVoteSmooth() {
		return voteSmooth;
	}
	public void setVoteSmooth(Integer voteSmooth) {
		this.voteSmooth = voteSmooth;
	}
	public String getStatisticalTime() {
		return statisticalTime;
	}
	public void setStatisticalTime(String statisticalTime) {
		this.statisticalTime = statisticalTime;
	}
	
}
