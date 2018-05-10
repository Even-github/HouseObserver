package per.even.HouseObserver.admin.controller;

import javax.print.DocFlavor.READER;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import per.even.HouseObserver.admin.service.AdminUserService;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;

@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
	@Autowired
	private HttpSession session;
	
	public static final String ADMINUSERID = "AdminUserId";
	
	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping("/login")
	public ResultResponse login(@RequestParam("id")String id, 
			@RequestParam("pwd")String pwd) {
		if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(pwd)) {
			boolean isSuccess = adminUserService.checkIdAndPwd(id, pwd); //验证结果
			if (isSuccess) { //验证通过
				session.setAttribute(ADMINUSERID, id);
				return new ResultResponse(true);
			} else {
				return new ResultResponse(false, "密码错误！");
			}
		} else {
			return new ResultResponse(false, "账号或者密码不得为空！");
		}
	}
	
	@RequestMapping("/updatePwd")
	public ResultResponse updatePwd(@RequestParam("oldPwd")String oldPwd,
			@RequestParam("newPwd")String newPwd,
			HttpServletRequest request) {
		if (!StringUtils.isEmpty(oldPwd) && !StringUtils.isEmpty(newPwd)) {
			String id = (String)session.getAttribute(ADMINUSERID);
			if (!StringUtils.isEmpty(id)) {
				boolean isSuccess = adminUserService.checkIdAndPwd(id, oldPwd); //验证结果
				if (isSuccess) {
					return new ResultResponse(adminUserService.updatePwd(id, newPwd));
				} else {
					return new ResultResponse(false, "原密码错误！");
				}
			} else {
				return new ResultResponse(false, "您已退出系统！");
			}
		} else {
			return new ResultResponse(false, "原密码不能为空！");
		}
	}

	@RequestMapping("/getAdminUserId")
	public DataResponse<String> getAdminUserId() {
		String id = (String)session.getAttribute(ADMINUSERID);
		return new DataResponse<String>(id);
	}
	
	@RequestMapping("/exit")
	public ResultResponse exit() {
		session.removeAttribute(ADMINUSERID);
		if (session.getAttribute(ADMINUSERID) == null) {
			return new ResultResponse(true);
		} else {
			return new ResultResponse(false);
		}
	}
}
