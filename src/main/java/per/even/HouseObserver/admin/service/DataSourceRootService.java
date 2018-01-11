package per.even.HouseObserver.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.even.HouseObserver.common.mapper.SpiderDataSourceRootMapper;
import per.even.HouseObserver.common.model.SpiderDataSourceRoot;
import per.even.HouseObserver.common.model.vo.Page;
import per.even.HouseObserver.common.service.AbstractService;

@Service
public class DataSourceRootService extends AbstractService<SpiderDataSourceRoot>{
	@Autowired
	private SpiderDataSourceRootMapper spiderDataSourceRootMapper;
	
	public List<SpiderDataSourceRoot> selectAll() {
		return spiderDataSourceRootMapper.selectAll();
	}

	@Override
	public List<SpiderDataSourceRoot> selectByPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpiderDataSourceRoot> selectByKeyword(Page page, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteByIds(List<String> ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SpiderDataSourceRoot selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateById(SpiderDataSourceRoot data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(SpiderDataSourceRoot data) {
		// TODO Auto-generated method stub
		return false;
	}
}
