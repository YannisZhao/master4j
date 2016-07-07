package com.zk.oms.basis;

import com.zk.oms.basis.exception.OmsDaoException;
import com.zk.oms.basis.sql.SqlParamWrapper;
import com.zk.oms.basis.vo.Paginator;

import java.util.List;

public interface OmsDao<T extends Domain> {
	
	public abstract boolean save(T obj) throws OmsDaoException;
	
	public abstract int batchSave(List<T> objs) throws OmsDaoException;
	
	public abstract boolean remove(T obj) throws OmsDaoException;
	
	public abstract int batchRemove(List<T> objs) throws OmsDaoException;
	
	public abstract int removeAll() throws OmsDaoException;
	
	public abstract boolean update(T obj) throws OmsDaoException;
	
	public abstract int batchUpdate(List<T> objs) throws OmsDaoException;
	
	public abstract T findById(T objs) throws OmsDaoException;
	
	public abstract List<T> findAll(SqlParamWrapper[] params) throws OmsDaoException;

	public abstract List<T> findByPage(SqlParamWrapper[] params, Paginator paginator) throws OmsDaoException;

	public abstract long getTotalRows(SqlParamWrapper[] params) throws OmsDaoException;
	
}
