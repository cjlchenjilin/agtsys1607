package org.agtsys.util;

public class MySqlPageTool {
	private Integer index;//当前页码
	private Integer size = 5; //页面大小，默认5条记录
	private Integer count;//总记录数
	private Integer total;//总页数
	//mysqls limit分页开始位置
	private Integer limit_begin;
	
	public MySqlPageTool(Integer index,Integer size)throws Exception{
		if(index==null){
			throw new Exception("当前页码index为null");
		}
		this.size=size;
		limit_begin = (index-1)*size;
	}
	
	
	//获取总页数
	public Integer getTotal(Integer count) throws Exception{
		if(count==null){
			throw new Exception("总记录数count为null");
		}
		return count%size==0?count/size:count/size+1;
	}


	//获取分页偏移
	public Integer getLimit_begin() {
		return limit_begin;
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}	
}
