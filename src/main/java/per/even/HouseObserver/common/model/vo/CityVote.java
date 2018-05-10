package per.even.HouseObserver.common.model.vo;

public class CityVote {
	private Integer voteRise; //投票上涨数量
	private Integer voteFall; //投票下跌数量
	private Integer voteSmooth; //投票平稳数量
	public Integer getVoteRise() {
		return voteRise;
	}
	public void setVoteRise(Integer voteRise) {
		this.voteRise = voteRise;
	}
	public Integer getVoteFall() {
		return voteFall;
	}
	public void setVoteFall(Integer voteFall) {
		this.voteFall = voteFall;
	}
	public Integer getVoteSmooth() {
		return voteSmooth;
	}
	public void setVoteSmooth(Integer voteSmooth) {
		this.voteSmooth = voteSmooth;
	}
	
}
