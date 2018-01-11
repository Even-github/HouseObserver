package per.even.HouseObserver.common.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class SpiderDataSourceRoot {
	@Id
	private int id;
	private String name;
	private String url;
	private String spider;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
