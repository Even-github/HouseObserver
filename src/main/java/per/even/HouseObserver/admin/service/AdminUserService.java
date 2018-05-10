package per.even.HouseObserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.common.mapper.AdminUserMapper;
import per.even.HouseObserver.common.model.AdminUser;

@Service
public class AdminUserService {
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	/**
	 * 验证账号面膜
	 * @param id
	 * @param pwd
	 * @return  true表示账号密码正确，false表示账号密码错误 
	 */
	public boolean checkIdAndPwd(String id, String pwd) {
		return adminUserMapper.selectCountByIdAndPwd(id, pwd) > 0;
	}
	
	public boolean updatePwd(String id, String newPwd) {
		AdminUser user = new AdminUser();
		user.setId(id);
		user.setPwd(newPwd);
		return adminUserMapper.updateByPrimaryKey(user) > 0;
	}
	
}
