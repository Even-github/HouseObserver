package per.even.HouseObserver.common.mapper;

import org.apache.ibatis.annotations.Param;

import per.even.HouseObserver.common.model.AdminUser;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface AdminUserMapper extends Mapper<AdminUser>{

	Integer selectCountByIdAndPwd(
			@Param(value = "id")String id, 
			@Param(value = "pwd")String pwd);
	
}
