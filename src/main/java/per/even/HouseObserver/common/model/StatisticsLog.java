package per.even.HouseObserver.common.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class StatisticsLog {
	@Id
	private Integer id;
	private String statisticsTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatisticsTime() {
		return statisticsTime;
	}
	public void setStatisticsTime(String statisticsTime) {
		this.statisticsTime = statisticsTime;
	}
	
}
