package com.bluemsun.entity;

import java.util.List;

public class Page<T>{
	
	//当前页数,无需计算
	
	private int pageNum;
	
	//每页的记录数，自己规定，无需计算
	
	private int pageSize;
	
	//总页数，计算
	
	private int totalPage;
	
	//数据库总的记录数，无需计算
	
	private Long totalRecord;
	
	//前端所显示的开始页数，需要计算
	
	private int start;
	
	//结束页数需要计算
	
	private int end;
	
	//Page对象
	
	private List<T> pageList;
	
	//开始索引，即从第几行开始拿需要计算
	
	private int startIndex;
	
	
	public Page(int pageNum, int pageSize, Long totalRecord) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		//计算pageNum
		
		if (totalRecord%pageSize==0){
			this.totalPage= (int) (totalRecord/pageSize);
		}
		else{
			this.totalPage= (int) (totalRecord/pageSize+1);
		}
		
		//startIndex
		this.startIndex=pageNum*pageSize-pageSize;
		
		//start,end
		
		if (totalPage<=7){
			this.start=1;
			this.end=pageSize;
		}
		else{
			//一共显示7页
			
			this.start=pageNum-3;
			this.end=pageNum+3;
			
			if(this.start<=0){
				this.start=1;
				this.end=7;
			}
			else if(this.end>totalPage){
				this.start=totalPage-7;
				this.end=totalPage;
			}
		}
		
		
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Long getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public List<T> getPageList() {
		return pageList;
	}
	
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}