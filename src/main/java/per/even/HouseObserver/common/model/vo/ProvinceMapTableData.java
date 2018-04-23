package per.even.HouseObserver.common.model.vo;

import java.util.List;
import java.util.Map;

public class ProvinceMapTableData {
	private List<ProvincePriceInfo> provincePriceInfo;
	private Map<String, Double> housingMapData;
	
	public Map<String, Double> getHousingMapData() {
		return housingMapData;
	}
	public void setHousingMapData(Map<String, Double> housingMapData) {
		this.housingMapData = housingMapData;
	}
	public List<ProvincePriceInfo> getProvincePriceInfo() {
		return provincePriceInfo;
	}
	public void setProvincePriceInfo(List<ProvincePriceInfo> provincePriceInfo) {
		this.provincePriceInfo = provincePriceInfo;
	}
	
}
