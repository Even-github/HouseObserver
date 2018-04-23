package per.even.HouseObserver.common.response;

import java.util.List;

public class DataListResponse<T> extends BaseResponse {
	private List<? extends T> data;
	private Integer pageNum;
	private Long total;
	
	public DataListResponse() {
		
	}
	
	public DataListResponse(List<? extends T> data) {
		this.code = "200";
		this.message = "ok";
		this.data = data;
	}
	
	public DataListResponse(List<? extends T> data, Integer pageNum, Long total) {
		this.code = "200";
		this.message = "ok";
		this.data = data;
		this.pageNum = pageNum;
		this.total = total;
	}

	public List<? extends T> getData() {
		return data;
	}

	public void setData(List<? extends T> data) {
		this.data = data;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Long getTotalPage() {
		return total;
	}

	public void setTotalPage(Long total) {
		this.total = total;
	}
	
}
