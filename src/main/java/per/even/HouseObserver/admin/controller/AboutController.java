package per.even.HouseObserver.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import per.even.HouseObserver.admin.service.AboutService;
import per.even.HouseObserver.common.model.About;
import per.even.HouseObserver.common.response.DataResponse;
import per.even.HouseObserver.common.response.ResultResponse;

@RestController
@RequestMapping("/about")
public class AboutController {
	@Autowired
	private AboutService aboutService;
	
	@RequestMapping("/getAbout")
	public DataResponse<About> getAbout() {
		return new DataResponse<About>(aboutService.getAbout());
	}
	
	@RequestMapping("/submitAbout")
	public ResultResponse submitAbout(String id, String content) {
		if (id != null) {
			return new ResultResponse(aboutService.updateAbout(id, content));
		} else {
			return new ResultResponse(false);
		}
			
	}
}
