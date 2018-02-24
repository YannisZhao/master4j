<#include "../../lib/declare.ftl"/>
<#include "../../lib/data.ftl"/>
package ${package};

${imports}
import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>

import ${basePackageName}.api.common.Pagination;
import ${basePackageName}.api.dto.${dtoName};

/**
${classDoc}
*/
public interface ${className} {

    /**
    * Save ${dtoName}
    *
    * @param ${dtoName?uncap_first}
    * @return Saved ${dtoName}
    */
    ${dtoName} save${dtoName}(${dtoName} ${dtoName?uncap_first});

    /**
    * Remove ${dtoName}
    *
    * @param <#list pks as pk>${pk.name}</#list> Record ID
    * @return true if success, or false if no record be removed
    */
    Boolean remove${dtoName}(<#list pks as pk>${pk.type} ${pk.name}</#list>);

    /**
    * Update ${dtoName}
    *
    * @param ${dtoName?uncap_first}
    * @return true if success, or false if no record be updated
    */
    Boolean update${dtoName}(${dtoName} ${dtoName?uncap_first});

    /**
    * Fetch ${dtoName}
    *
    * @param <#list pks as pk>${pk.name}</#list> Record ID
    * @return ${dtoName}
    */
    ${dtoName} get${dtoName}ById(<#list pks as pk>${pk.type} ${pk.name}</#list>);

    /**
    * Fetch ${dtoName} list by offset
    */
    Pagination<${dtoName}> list${dtoName}ByOffset(Integer offset, Integer count);

}