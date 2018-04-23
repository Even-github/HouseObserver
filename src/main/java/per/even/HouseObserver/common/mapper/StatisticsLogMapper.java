package per.even.HouseObserver.common.mapper;

import per.even.HouseObserver.common.model.StatisticsLog;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface StatisticsLogMapper extends Mapper<StatisticsLog> {
	String selectLastStatisticsTime();
}
