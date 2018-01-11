package per.even.HouseObserver.common.mapper;

import java.util.List;

import per.even.HouseObserver.common.model.House;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface HouseMapper extends Mapper<House>{
	List<House> selectByKeyword(String keyword);
	int bulkDeleteById(List<String> id);
}
