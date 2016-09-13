package ${package};

${imports}

/**
${classDoc}
*/
public interface ${className} {

    public abstract ${dtoName} findById(String id) throws DaoException;

    public abstract List<${dtoName}> findByPage(SqlParamWrapper[] params, Paginator paginator) throws DaoException;

    /**
    * Statistic with filter(s)
    * @return total rows number in filtered condition
    */
    long getTotalRows(GeneralQueryRequest request);

    public abstract boolean save(${dtoName} obj) throws DaoException;

    public abstract int batchSave(List<${dtoName}> objs) throws DaoException;

    public abstract boolean remove(String id) throws DaoException;

    public abstract int batchRemove(String[] ids) throws DaoException;

    public abstract boolean update(${dtoName} obj) throws DaoException;

    public abstract int batchUpdate(List<${dtoName}> objs) throws DaoException;

}