package util;

public class Pagination {
	private int ye;
	private int maxYe;
	private int beginYe;
	private int endYe;
	private int begin;
	//numInPage 一页显示多少条记录  numOfPage 一页显示多少页码
	public Pagination(int ye,int count,int numInpage,int numOfPage){
		
		this.ye= ye;
		if (this.ye <= 1) {
			this.ye = 1;
		}
		
	 maxYe = 0;
		if (count % numInpage == 0) {
			maxYe = count / numInpage;
		} else {
			maxYe = count / numInpage + 1;
		}
		if (this.ye >= maxYe) {
			this.ye = maxYe;
		}

		 begin = (this.ye - 1) * numInpage;
		 beginYe = this.ye - numOfPage/2;
		if (beginYe <= 1) {
			beginYe = 1;
		}
		 endYe = beginYe + numOfPage-1;
		if (endYe >= maxYe) {
			endYe = maxYe;
		}
		
	}
	
	public int getYe() {
		return ye;
	}
	public int getMaxYe() {
		return maxYe;
	}
	public int getBeginYe() {
		return beginYe;
	}
	public int getEndYe() {
		return endYe;
	}
	public int getBegin() {
		return begin;
	}
	
	
	
	

}
