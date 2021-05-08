package base.model;

/**
 * 排序信息
 * @ClassName SortInfo 
 * @Description 
 * @author Marvis
 * @date 2016年6月14日 下午6:09:57
 */
public class SortInfo {
	/**
	 * 排序列(字段)
	 */
	private Byte sortCol;
	/**
	 * 排序方向
	 */
	private Byte sortDirect;
	
	/**
	 * 排序列(字段)
	 */
	 public Byte getSortCol() {
		return sortCol;
	}
	
	 /**
	  * 排序列(字段)
	  */
	public void setSortCol(Byte sortCol) {
		this.sortCol = sortCol;
	}
	
	/**
	 * 排序方向
	 */
	public Byte getSortDirect() {
		return sortDirect;
	}
	
	/**
	 * 排序方向
	 */
	public void setSortDirect(Byte sortDirect) {
		this.sortDirect = sortDirect;
	}
}

