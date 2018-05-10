package per.even.HouseObserver.filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.even.HouseObserver.common.response.BaseResponse;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;

@RestController
@RequestMapping("/noPermisson")
public class FilterController {
	
	@RequestMapping
	public BaseResponse noPermisson() {
		BaseResponse response = new BaseResponse();
		response.setCode("302");
		response.setMessage("无权访问，请登录！");
		return response;
	}
}
