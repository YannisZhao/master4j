<#include "../../lib/data.ftl"/>
package ${package};

${imports}
import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>

import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;

import ${basePackageName}.api.dto.${dtoName};

/**
${classDoc}
*/
public interface ${className} {

    <@compress_single_line>
    ${dtoName} findById(${idParams});
    </@compress_single_line>

    PageBean<?> findByPage(Paginator paginator);

    PageBean<?> search(GeneralQueryRequest request);

    boolean save(${dtoName} obj);
    
    int batchSave(List<${dtoName}> objs);

    <@compress_single_line>
    boolean remove(${idParams});
    </@compress_single_line>

    <@compress_single_line>
    int batchRemove(${batchRemoveParams});
    </@compress_single_line>

    boolean update(${dtoName} obj);

    int batchUpdate(List<${dtoName}> objs);

}