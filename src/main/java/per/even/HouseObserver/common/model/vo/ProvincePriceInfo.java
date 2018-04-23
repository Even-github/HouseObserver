package per.even.HouseObserver.common.model.vo;

public class ProvincePriceInfo {
	private String province;
	private Double provinceAveragePrice;
	private Double provinceRate;
	private String statisticalTime; //统计时间
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Double getProvinceAveragePrice() {
		return provinceAveragePrice;
	}
	public void setProvinceAveragePrice(Double provinceAveragePrice) {
		this.provinceAveragePrice = provinceAveragePrice;
	}
	public Double getProvinceRate() {
		return provinceRate;
	}
	public void setProvinceRate(Double provinceRate) {
		this.provinceRate = provinceRate;
	}
	public String getStatisticalTime() {
		return statisticalTime;
	}
	public void setStatisticalTime(String statisticalTime) {
		this.statisticalTime = statisticalTime;
	}
	
}
