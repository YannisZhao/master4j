/** 
 * Project Name:uaas 
 * File Name:PageVO.java 
 * Package Name:org.yannis.uaas.vo 
 * Date:2015年11月26日下午5:11:04 
 * Copyright (c) 2015, zhaoyjun0222@gmail.com All Rights Reserved. 
 * 
 */  
      
package org.yannis.uaas.vo;

/** 
 * ClassName:PageVO <br/> 
 * Function: Request VO of page. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年11月26日 下午5:11:04 <br/> 
 * @author   Yanjun Zhao , zhaoyjun0222@gmail.com
 * @version  1.0 
 * @since    JDK 1.7 
 * @see       
 */
public abstract class PageVO {
	
	/**
	 * Current page number
	 */
	protected int page;
	
	/**
	 * Rows per page
	 */
	protected int rows;
	
	/**
	 * The item used by result sort
	 */
	protected String sort;
	
	/**
	 * Sort strategy
	 */
	protected String order;

	/** 
	 * page. 
	 * 
	 * @return the page 
	 */
	public int getPage() {
		return page;
	}

	/** 
	 * page. 
	 * 
	 * @param page 
	 *			the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/** 
	 * rows. 
	 * 
	 * @return the rows 
	 */
	public int getRows() {
		return rows;
	}

	/** 
	 * rows. 
	 * 
	 * @param rows 
	 *			the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/** 
	 * sort. 
	 * 
	 * @return the sort 
	 */
	public String getSort() {
		return sort;
	}

	/** 
	 * sort. 
	 * 
	 * @param sort 
	 *			the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/** 
	 * order. 
	 * 
	 * @return the order 
	 */
	public String getOrder() {
		return order;
	}

	/** 
	 * order. 
	 * 
	 * @param order 
	 *			the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	
	public int getRowStart(){
		return (this.page-1)*this.rows;
	}
	
	public int getPageSize(){
		return this.rows;
	}

}
  