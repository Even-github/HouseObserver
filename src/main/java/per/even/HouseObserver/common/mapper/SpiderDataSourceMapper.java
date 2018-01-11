package per.even.HouseObserver.common.mapper;

import java.util.List;

import per.even.HouseObserver.common.model.SpiderDataSource;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface SpiderDataSourceMapper extends Mapper<SpiderDataSource>{
	List<SpiderDataSource> selectByKeyword(String keyword);
	int bulkDeleteById(List<String> ids);
	int countBySourceName(String name);
}
