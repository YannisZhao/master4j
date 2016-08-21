package ${package};

${imports}

/**
${classDoc}
*/
public interface ${className} {

    public abstract boolean save(${domainName} obj) throws DaoException;

    public abstract int batchSave(List<${domainName}> objs) throws DaoException;

    public abstract boolean remove(${domainName} obj) throws DaoException;

    public abstract int batchRemove(List<${domainName}> objs) throws DaoException;

    public abstract int removeAll() throws DaoException;

    public abstract boolean update(${domainName} obj) throws DaoException;

    public abstract int batchUpdate(List<${domainName}> objs) throws DaoException;

    public abstract ${domainName} findById(${domainName} objs) throws DaoException;

    public abstract List<${domainName}> findAll(SqlParamWrapper[] params) throws DaoException;

    public abstract List<${domainName}> findByPage(SqlParamWrapper[] params, Paginator paginator) throws DaoException;

    public abstract long getTotalRows(SqlParamWrapper[] params) throws DaoException;

}