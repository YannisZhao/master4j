<#include "../../lib/data.ftl"/>
package ${package};

import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>
import org.apache.ibatis.annotations.Param;

import ${basePackageName}.entity.${entityName};
${imports}

/**
${classDoc}
*/
public interface ${className} {

    /**
    * Insert new record
    *
    * @param entity record
    * @return  the new record id
    */
    <#list pks as pk>${pk.type}</#list> insert(${entityName} entity);

    /**
    * Delete record by the records primary key
    *
    * @param id primary key value
    * @return  the affected rows count
    */
    Integer deleteById(<#list pks as pk>@Param("${pk.name}") ${pk.type} ${pk.name}<#sep>, </#list>);

    /**
    * Batch delete by primary key value list
    *
    * @param ids
    * @return the affected rows count
    */
    Integer batchDeleteByIds(List<<#list pks as pk>${pk.type}</#list>> ids);

    /**
    * Update record by primary key
    *
    * @param entity new data to update
    * @return  the affected rows count
    */
    Integer updateById(${entityName} entity);

    /**
    * Fetch record with specified primary key value
    *
    * @param id the record primary key value
    * @return  the fetched data
    */
    ${entityName} selectById(<#list pks as pk>@Param("${pk.name}") ${pk.type} ${pk.name}<#sep>, </#list>);

    /**
    * Batch select data with specified primary keys
    *
    * @param ids the primary keys to fetch
    * @return the fetched data
    */
    List<${entityName}> selectByIds(List<<#list pks as pk>${pk.type}</#list>> ids);

}