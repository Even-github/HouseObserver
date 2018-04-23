package per.even.HouseObserver.common.model.vo;

import java.util.List;

public class CityNewPriceBean {
	private CityPriceMainInfo cityPriceMainInfo;
	private PriceChart cityPriceChart;
	private List<CountyPriceInfo> countyPriceInfoList;
	public CityPriceMainInfo getCityPriceMainInfo() {
		return cityPriceMainInfo;
	}
	public void setCityPriceMainInfo(CityPriceMainInfo cityPriceMainInfo) {
		this.cityPriceMainInfo = cityPriceMainInfo;
	}

	public List<CountyPriceInfo> getCountyPriceInfoList() {
		return countyPriceInfoList;
	}
	public void setCountyPriceInfoList(List<CountyPriceInfo> countyPriceInfoList) {
		this.countyPriceInfoList = countyPriceInfoList;
	}
	public PriceChart getCityPriceChart() {
		return cityPriceChart;
	}
	public void setCityPriceChart(PriceChart cityPriceChart) {
		this.cityPriceChart = cityPriceChart;
	}
	
}
