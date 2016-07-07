package com.zk.oms.basis.exception;

public enum ExceptionMeta {

	E10000(10000,""),
	E40001(40001,"Duplicate primary key"),
	E40002(40002,"Error generate primary key expression"),
	E40003(40003,"Null pointer error"), 
	E40004(40004,"Error fetch value of Object"), 
	E40005(40005,"Cannot get specified data"), 
	E40006(40006,"Error save data into database"), 
	E50001(50001,"Error happened when invoking service with third part host"),
	E60001(60001,"Error happend when generating data"),
	E60002(60002,"Error happend when parsing data"),
	E70001(70001,"Loading configeration data failed");
	
	ExceptionMeta(int code, String message){
		this.code = code;
		this.message = message;
	}
	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString(){
		return this.code +","+ this.message;
	}
	
}
