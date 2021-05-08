package base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 分页查询参数类-扩展
 * <p/>
 * <b>注</b>:可传入bean的id,返回大于id之后数据第一条开始后的pageSize条数据<br/>
 * &nbsp&nbsp&nbsp&nbsp参数currentPage(请求页面)失效<br/>
 * <p/>
 * 对外暴露参数:pageSize
 * 
 * @author
 *
 */
public class PageInfoEx extends PageInfo {
	
	/**
	 * bean起始坐标(不包含)
	 */
	private Integer pageBeginId = 1;

	/**
	 * 分页扩展类中,此方法已失效<b>
	 * @param infoCount
	 *            记录总数
	 * @param pageSize
	 *            每页显示个数
	 * @param currPage
	 *            当前页码
	 * @see com.yjrg.core.base.model.PageInfoExtend#setPageParams(int)
	 * @deprecated 分页扩展类中,此方法已失效
	 */
	public void setPageParams(int totalCount, int pageSize, int currentPage) {
		
	}
	/**
	 * 将分布参数传入处理，最终计算出当前页码PageQuery_currPage，开始坐标PageQuery_star，结束坐标PageQuery_end，总页数PageQuery_Psize
	 * @param infoCount 记录总数
	 */
	public void setPageParams(int totalCount) {
		if(this.pageBeginId != null) {
			this.currentPage = 1;
			this.pageSize = Default_PageSize;
			this.pageBegin = 0;
			this.pageEnd = this.currentPage * this.pageSize;
			this.totalCount = totalCount;
			this.totalPage = this.pageSize == 0 ? 1 : (int) Math.ceil((double) this.totalCount / (double) this.pageSize);;
			return;
		}
	}

	/**
	 * bean起始id(不包含)
	 */
	public Integer getPageBeginId() {
		return pageBeginId;
	}

	/**
	 * bean起始id(不包含)
	 */
	public void setPageBeginId(Integer pageBeginId) {
		this.pageBeginId = pageBeginId;
	}
	@JsonIgnore
	public int getCurrentPage() {
		return currentPage;
	}
	
	@JsonIgnore
	public int getPageBegin() {
		return pageBegin;
	}

	@JsonIgnore
	public int getPageEnd() {
		return pageEnd;
	}

	@Override
	@Deprecated
	public void setCurrentPage(int currentPage) {
		//this.currentPage = currentPage;
	}
}
