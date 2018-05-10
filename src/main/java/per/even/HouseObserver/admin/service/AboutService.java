package per.even.HouseObserver.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.common.mapper.AboutMapper;
import per.even.HouseObserver.common.model.About;

@Service
public class AboutService {
	@Autowired
	private AboutMapper aboutMapper;
	
	public About getAbout() {
		List<About> list = aboutMapper.selectAll();
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public boolean updateAbout(String id, String content) {
		About about = new About();
		about.setId(id);
		about.setContent(content);
		return aboutMapper.updateByPrimaryKey(about) > 0;
	}
	
}
