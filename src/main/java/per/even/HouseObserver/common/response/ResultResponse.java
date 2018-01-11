package per.even.HouseObserver.common.response;

public class ResultResponse extends BaseResponse{
	private Boolean success;
	private String description;

	public ResultResponse() {
		this.code = "200";
		this.message = "ok";
	}
	
	public ResultResponse(boolean success) {
		this.code = "200";
		this.message = "ok";
		this.success = success;
	}
	
	public ResultResponse(boolean success, String decription) {
		this.code = "200";
		this.message = "ok";
		this.success = success;
		this.description = decription;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
