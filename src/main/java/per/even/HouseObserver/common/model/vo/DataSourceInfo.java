package per.even.HouseObserver.common.model.vo;

public class DataSourceInfo {
	private String name; //来源名称，如安居客
	private int count;  //数据来源数量
	private String url; //数据来源根url
	private String spider; //爬取数据来源的爬虫名称
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSpider() {
		return spider;
	}
	public void setSpider(String spider) {
		this.spider = spider;
	}
}
