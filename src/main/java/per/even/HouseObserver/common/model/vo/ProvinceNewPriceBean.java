package per.even.HouseObserver.common.model.vo;

import java.util.List;

public class ProvinceNewPriceBean {
	private ProvinceNewPriceInfo provinceNewPriceInfo;
	private PriceChart provinceNewPriceChart;
	private List<CityPriceAndRate> cityPriceInfoList;
	
	public ProvinceNewPriceInfo getProvinceNewPriceInfo() {
		return provinceNewPriceInfo;
	}
	public void setProvinceNewPriceInfo(ProvinceNewPriceInfo provinceNewPriceInfo) {
		this.provinceNewPriceInfo = provinceNewPriceInfo;
	}
	public PriceChart getProvinceNewPriceChart() {
		return provinceNewPriceChart;
	}
	public void setProvinceNewPriceChart(PriceChart provinceNewPriceChart) {
		this.provinceNewPriceChart = provinceNewPriceChart;
	}
	public List<CityPriceAndRate> getCityPriceInfoList() {
		return cityPriceInfoList;
	}
	public void setCityPriceInfoList(List<CityPriceAndRate> cityPriceInfoList) {
		this.cityPriceInfoList = cityPriceInfoList;
	}
	
}
