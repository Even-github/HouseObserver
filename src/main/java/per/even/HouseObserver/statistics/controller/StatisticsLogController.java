package per.even.HouseObserver.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.statistics.service.StatisticsLogService;

@RestController
@RequestMapping("/statisticsLog")
public class StatisticsLogController {
	@Autowired
	private StatisticsLogService statisticsLogService;
	
	@RequestMapping("/getLastStatisticsTime")
	public DataResponse<String> getLastStatisticsTime() {
		return new DataResponse<>(statisticsLogService.getLastTime());
	}
}
