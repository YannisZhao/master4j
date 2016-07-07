package ${package};

${imports}

import org.yannis.test.dao.${baseClassName};

/**
${classDoc}
*/
public class ${className} implements ${baseClassName} {

    private final BeanPropertyRowMapper<${domainName}> rowMapper = BeanPropertyRowMapper.newInstance(${domainName}.class);

    @Override
    public abstract boolean save(${domainName} obj) throws DaoException {
        return false;
    };

    @Override
    public abstract int batchSave(List<${domainName}> objs) throws DaoException {
        return 0;
    };

    @Override
    public abstract boolean remove(${domainName} obj) throws DaoException {
        return false;
    };

    @Override
    public abstract int batchRemove(List<${domainName}> objs) throws DaoException {
        return 0;
    };

    @Override
    public abstract int removeAll() throws DaoException {
        return 0;
    };

    @Override
    public abstract boolean update(${domainName} obj) throws DaoException {
        return false;
    };

    @Override
    public abstract int batchUpdate(List<${domainName}> objs) throws DaoException {
        return 0;
    };

    @Override
    public abstract ${domainName} findById(${domainName} objs) throws DaoException {
        return null;
    };

    @Override
    public abstract List<${domainName}> findAll(SqlParamWrapper[] params) throws DaoException {
        return null;
    };

    @Override
    public abstract List<${domainName}> findByPage(SqlParamWrapper[] params, Paginator paginator) throws DaoException {
        return null;
    };

    @Override
    public abstract long getTotalRows(SqlParamWrapper[] params) throws DaoException {
        return null;
    };
}