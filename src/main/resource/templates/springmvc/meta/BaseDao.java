package com.zk.oms.basis;

import com.zk.oms.basis.sql.SqlInsertionWrapper;
import com.zk.oms.basis.sql.SqlParamWrapper;
import com.zk.oms.basis.sql.SqlUpdateWrapper;
import com.zk.oms.basis.sql.SqlWhereWrapper;
import com.zk.oms.cache.CacheManager;
import com.zk.oms.util.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDao {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected SqlManager sqlManager;
	
	@Autowired
	protected CacheManager cacheManager;
	
	protected SqlWhereWrapper where(SqlParamWrapper[] params) {
		return SqlUtils.where(params);
	}
	
	protected SqlInsertionWrapper insertion(String tableName, Domain obj){
		return SqlUtils.insertion(tableName,obj);
	}
	
	protected SqlUpdateWrapper updation(String tableName, Domain obj){
		return SqlUtils.update(tableName,obj);
	}

}
