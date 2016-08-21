package com.zk.oms.basis;

import com.zk.oms.basis.exception.OmsServiceException;
import com.zk.oms.basis.sql.SqlParamWrapper;
import com.zk.oms.basis.vo.PageBean;
import com.zk.oms.basis.vo.Paginator;

import java.util.List;

/**
 * <p>Base Service</p>
 * 
 * T: Domain object<br/>
 * 
 * F: Form object
 * 
 */
public interface Service<T extends Domain> {
	
	public abstract boolean save(T obj) throws OmsServiceException;
	
	public abstract int batchSave(List<T> objs) throws OmsServiceException;
	
	public abstract boolean remove(T obj) throws OmsServiceException;
	
	public abstract int batchRemove(List<T> objs) throws OmsServiceException;
	
	public abstract boolean update(T obj) throws OmsServiceException;
	
	public abstract int batchUpdate(List<T> objs) throws OmsServiceException;
	
	public abstract T findById(T objs) throws OmsServiceException;
	
	public abstract List<T> findAll(SqlParamWrapper[] params) throws OmsServiceException;

	public abstract PageBean<T> findByPage(SqlParamWrapper[] params, Paginator paginator) throws OmsServiceException;

}
