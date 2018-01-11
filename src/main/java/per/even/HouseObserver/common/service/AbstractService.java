package per.even.HouseObserver.common.service;

import java.util.List;

import per.even.HouseObserver.common.model.vo.Page;

public abstract class AbstractService<T> {
	public abstract List<T> selectByPage(Page page);
	public abstract List<T> selectByKeyword(Page page, String keyword);
	public abstract boolean deleteByIds(List<String> ids);
	public abstract T selectById(String id);
	public abstract boolean updateById(T data);
	public abstract boolean insert(T data);
}
