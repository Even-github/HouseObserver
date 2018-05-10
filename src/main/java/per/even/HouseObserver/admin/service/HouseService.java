package per.even.HouseObserver.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import per.even.HouseObserver.common.mapper.HouseMapper;
import per.even.HouseObserver.common.model.House;
import per.even.HouseObserver.common.model.vo.CityAveragePrice;
import per.even.HouseObserver.common.model.vo.CountyAveragePrice;
import per.even.HouseObserver.common.model.vo.Page;
import per.even.HouseObserver.common.model.vo.ProvinceAveragePrice;
import per.even.HouseObserver.common.service.AbstractService;

@Service
public class HouseService extends AbstractService<House>{
	@Autowired
	private HouseMapper houseMapper;

	@Override
	public List<House> selectByPage(Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return houseMapper.selectAll();
	}

	@Override
	public List<House> selectByKeyword(Page page, String keyword) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return houseMapper.selectByKeyword(keyword);
	}

	@Override
	public boolean deleteByIds(List<String> ids) {
		return houseMapper.bulkDeleteById(ids) > 0;
	}

	@Override
	public House selectById(String id) {
		return houseMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateById(House data) {
		return houseMapper.updateByPrimaryKeySelective(data) > 0;
	}

	@Override
	public boolean insert(House data) {
		return houseMapper.insertSelective(data) > 0;
	}
	
	public List<ProvinceAveragePrice> selectProvinceAveragePriceByCrawlTime(Double beginTime,
			Double endTime) {
		return houseMapper.selectProvinceAveragePriceByCrawlTime(beginTime, endTime);
	}

	public List<CityAveragePrice> selectCityAveragePriceByCrawlTime(Double beginTime, Double endTime) {
		return houseMapper.selectCityAveragePriceByCrawlTime(beginTime, endTime);
	}

	public Double selectCityAveragePriceByType(String city, String type, 
			Double beginTime, Double endTime) {
		return houseMapper.selectCityAveragePriceByType(city, type, beginTime, endTime);
	}

	public List<CountyAveragePrice> selectAveragePriceByCityCounty(
			String city, Double beginTime, Double endTime) {
		return houseMapper.selectAveragePriceByCityCounty(city, beginTime, endTime);
	}

	public List<String> selectCityByCrawlTime(Double beginTime, Double endTime) {
		return houseMapper.selectCityByCrawlTime(beginTime, endTime);
	}
	
}
