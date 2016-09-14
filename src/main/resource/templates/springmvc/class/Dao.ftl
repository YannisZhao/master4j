package ${package};

import java.util.List;

import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;

import ${basePackageName}.domain.${domainName};
${imports}

/**
${classDoc}
*/
public interface ${className} {

    public abstract ${domainName} findById(String id) throws DaoException;

    public abstract List<${domainName}> findByPage(SqlParamWrapper[] params, Paginator paginator) throws DaoException;

    /**
    * Statistic with filter(s)
    * @return total rows number in filtered condition
    */
    long getTotalRows(GeneralQueryRequest request);

    public abstract boolean save(${domainName} obj) throws DaoException;

    public abstract int batchSave(List<${domainName}> objs) throws DaoException;

    public abstract boolean remove(String id) throws DaoException;

    public abstract int batchRemove(String[] ids) throws DaoException;

    public abstract boolean update(${domainName} obj) throws DaoException;

    public abstract int batchUpdate(List<${domainName}> objs) throws DaoException;

}