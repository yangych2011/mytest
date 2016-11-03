/**
 * 
 */
package com.puck.framework.service.pagination;

/**
 * @author yangyongchao
 * @descript
 * @name Paginator.java
 * @date 2015年12月23日
 */
public class Paginator {

	public static final int DEFAULT_PAGESIZE = 20;// 默认每页20条记录
	private static final int DEFAULT_PAGENO = 1;// 默认页号1

	private int pageSize = DEFAULT_PAGESIZE;// 每页记录数
	private int pageNo = DEFAULT_PAGENO;// 页号
	private int totalPage = 0;// 总页数
	private int totalRecord = 0;// 总记录数
	private int startIndex = 0;// 起始索引
	private int endIndex;// 结束索引

	private boolean doPagination = true;// 是否分页，设置为false，即使sqlid符合拦截规则，依旧不分页
	private boolean queryTotal = true;// 是否查询总数

	public int getPageSize() {

		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGESIZE;
		}
		this.pageSize = pageSize;
	}

	public int getPageNo() {

		return pageNo;
	}

	public void setPageNo(int pageNo) {

		if (pageNo < 1) {
			pageNo = 1;
		}
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		if (totalRecord % getPageSize() == 0) {
			totalPage = totalRecord / getPageSize();
		} else {
			totalPage = totalRecord / getPageSize() + 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getStartIndex() {

		startIndex = (pageNo - 1) * pageSize;
		if (startIndex < 0) {
			startIndex = 0;
		}
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {

		endIndex = pageNo * pageSize;
		if (endIndex > totalRecord) {
			endIndex = totalRecord;
		}

		return endIndex - 1;// 从0计数
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public boolean isDoPagination() {
		return doPagination;
	}

	public void setDoPagination(boolean doPagination) {
		this.doPagination = doPagination;
	}

	public boolean isQueryTotal() {
		return queryTotal;
	}

	public void setQueryTotal(boolean queryTotal) {
		this.queryTotal = queryTotal;
	}

}
