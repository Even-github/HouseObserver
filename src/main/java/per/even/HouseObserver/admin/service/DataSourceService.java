package per.even.HouseObserver.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import per.even.HouseObserver.common.mapper.SpiderDataSourceMapper;
import per.even.HouseObserver.common.model.SpiderDataSource;
import per.even.HouseObserver.common.model.vo.Page;
import per.even.HouseObserver.common.service.AbstractService;

@Service
public class DataSourceService extends AbstractService<SpiderDataSource>{
	@Autowired
	private SpiderDataSourceMapper spiderDataSourceMapper;
	
	public List<SpiderDataSource> selectByPage(Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return spiderDataSourceMapper.selectAll();
	}
	
	public List<SpiderDataSource> selectByKeyword(Page page, String keyword) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return spiderDataSourceMapper.selectByKeyword(keyword);
	}
	
	public boolean deleteByIds(List<String> ids) {
		return spiderDataSourceMapper.bulkDeleteById(ids) > 0;
	}
	
	public SpiderDataSource selectById(String id) {
		return spiderDataSourceMapper.selectByPrimaryKey(id);
	}
	
	public boolean updateById(SpiderDataSource data) {
		return spiderDataSourceMapper.updateByPrimaryKeySelective(data) > 0;
	}
	
	public boolean insert(SpiderDataSource data) {
		return spiderDataSourceMapper.insertSelective(data) > 0;
	}
	
	/**
	 * 根据source_name计算信息数量
	 * @return
	 */
	public int countBySourceName(String name) {
		return spiderDataSourceMapper.countBySourceName(name);
	}
}
