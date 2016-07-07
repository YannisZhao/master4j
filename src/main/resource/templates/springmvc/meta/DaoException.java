package com.zk.oms.basis.exception;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(){
		super();
	}
	public DaoException(ExceptionMeta meta){
		this.meta = meta;
	}
	private ExceptionMeta meta;
	
	@Override
	public String toString(){
		return this.meta.toString() +"\n"+ this.getMessage();
	}

}
