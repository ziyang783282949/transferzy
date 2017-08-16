package com.spirit.porker.dao.pagination;

import java.io.Serializable;

public class PaginationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5596084315954282504L;

	// default value
	private static final Integer defaultCurrentPage = 1;
	private static final Integer defaultRecordPerPage = 10;

	/**
	 * 当前页，从1开始
	 */
	private Integer currentPage = null;
	/**
	 * 每页记录数
	 */
	private Integer recordPerPage = null;
	/**
	 * 总页数
	 */
	private Integer totalPage = null;
	/**
	 * 总记录数
	 */
	private Integer totalRecord = null;

	public PaginationInfo() {
		
		this.currentPage = defaultCurrentPage;
		this.recordPerPage = defaultRecordPerPage;
		this.totalPage = 1;
		this.totalRecord = 1;
		
		
	}

	public PaginationInfo(Integer currentPage, Integer recordPerPage) {
		this.setCurrentPage(currentPage);
		this.setRecordPerPage(recordPerPage);
	}

	public int getOffset() {
		return this.getRecordPerPage() * (this.getCurrentPage() - 1);
	}

	public int getLimit() {
		return this.getRecordPerPage();
	}

	public static PaginationInfo getDefault() {
		return new PaginationInfo(defaultCurrentPage, defaultRecordPerPage);
	}

	public Integer getCurrentPage() {
		return currentPage == null ? defaultCurrentPage : currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = (currentPage == null || currentPage <= 0) ? defaultCurrentPage : currentPage;
	}

	public Integer getRecordPerPage() {
		return recordPerPage == null ? defaultRecordPerPage : recordPerPage;
	}

	public void setRecordPerPage(Integer recordPerPage) {
		this.recordPerPage = (recordPerPage == null || recordPerPage <= 0) ? defaultRecordPerPage : recordPerPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return this.getCurrentPage() + "/" + this.getRecordPerPage();
	}
}
