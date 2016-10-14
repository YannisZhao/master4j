<#include "../../lib/data.ftl"/>
package ${package};

import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>

import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;

import ${basePackageName}.domain.${domainName};
${imports}

/**
${classDoc}
*/
public interface ${className} {

    /**
     * Find entity by id
     * @param id
     * @return Entity or null if not found
     */
    <@compress_single_line>
    ${domainName} findById(${idParams});
    </@compress_single_line>

    /**
     * Find entities by page
     * @param paginator
     * @return
     */
    List<${domainName}> findByPage(Paginator paginator);

    /**
     * Search records
     * @return List of filtered records
     */
    List<${domainName}> search(GeneralQueryRequest request);

    /**
     * Statistic with no filter
     * @return total rows number
     */
    long getTotalRows();

    /**
     * Statistic with filter(s)
     * @return total rows number in filtered condition
     */
    long getTotalRows(GeneralQueryRequest request);

    boolean save(${domainName} obj);

    int batchSave(List<${domainName}> objs);

    /**
    * Remove entity with specified id
    * @param id
    * @return
    */
    <@compress_single_line>
    boolean remove(${idParams});
    </@compress_single_line>

    <@compress_single_line>
    int batchRemove(${batchRemoveParams});
    </@compress_single_line>

    boolean update(${domainName} obj);

    int batchUpdate(List<${domainName}> objs);

}