package per.even.HouseObserver.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;
import per.even.HouseObserver.statistics.service.StatisticsManagerService;

@RestController
@RequestMapping("/statisticsManager")
public class StatisticsManagerController {
	@Autowired
	private StatisticsManagerService statisticsManagerService;
	
	@RequestMapping("/statisticsAll")
	public ResultResponse statisticsAll(Double endTime) {
		if (endTime != null) {
			String result = statisticsManagerService.statisticsAll(endTime);
			if (result.equals(StatisticsManagerService.RUNNING)) {
				return new ResultResponse(false, "另外一个进程正在进行统计，请勿重复操作！");
			} else if (result.equals(StatisticsManagerService.SUCCESS)){
				return new ResultResponse(true);
			} else {
				return new ResultResponse(false, "统计失败！");
			}
		} else {
			return new ResultResponse(false, "统计失败，结束时间不能为空！");
		}
	}
	
	@RequestMapping("/getStatisticsState")
	public DataResponse<Boolean> getStatisticsState() {
		return new DataResponse<Boolean> (statisticsManagerService.isRunning(false));
	}
}
