package com.bluemsun.entity;

import java.util.List;

public class Page<T> {
	//可获得数据
	private int pageNum;//当前页,从请求那边传过来。
	private int pageSize;//每页显示的数据条数。
	private Long totalRecord;//总的记录条数。查询数据库得到的数据
	List<T> list;
	//需要计算数据
	private long totalPage;//总页数，通过totalRecord和pageSize计算可以得来
	private int startIndex;//开始索引，也就是我们在数据库中要从第几行数据开始拿，有了startIndex和pageSize， 就知道了limit语句的两个数据，就能获得每页需要显示的数据了
	private long start;
	private long end;//分页显示的页数,比如在页面上显示1，2，3，4，5页，start就为1，end就为5，这个也是算过来的
	public Page(int pageNum,int pageSize,Long totalRecord){
		this.pageNum=pageNum;
		this.pageSize=pageSize;
		this.totalRecord=totalRecord;
		//计算totalPage
		if(totalRecord%pageSize==0){
			this.totalPage=totalRecord/pageSize;
		}else {
			this.totalPage=totalRecord/pageSize+1;
		}
		//计算startIndex
		this.startIndex=pageNum*pageSize-pageSize;
		//计算start、end默认是5
		if(totalPage<=5){
			this.start=1;
			this.end=totalPage;
		}else{
			this.start=pageNum-2;
			this.end=pageNum+2;
			if(this.start<=0){
				this.start=1;
				this.end=5;
			}
			if(this.end>totalPage){
				this.end=totalPage;
				this.start=this.end-5;
			}
		}
		
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
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public Long getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
	public Long getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public Long getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
}

