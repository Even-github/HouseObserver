package per.even.HouseObserver.common.response;

public class DataResponse<T> extends BaseResponse{
	private T data;

	public DataResponse() {
		String code = "200";
		String message = "ok";
	}
	
	public DataResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public DataResponse(T data) {
		this.code = "200";
		this.message = "ok";
		this.data = data;
	}
	
	public DataResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
