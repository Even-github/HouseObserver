package per.even.HouseObserver.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.common.mapper.StatisticsLogMapper;
import per.even.HouseObserver.common.model.StatisticsLog;

@Service
public class StatisticsLogService {
	@Autowired
	private StatisticsLogMapper mapper;
	
	public boolean insert(StatisticsLog log) {
		return mapper.insert(log) > 0;
	}
	
	public String getLastTime() {
		return mapper.selectLastStatisticsTime();
	}
}
