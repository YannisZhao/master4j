<#include "../../lib/declare.ftl"/>
<#include "../../lib/data.ftl"/>
package ${package};

${imports}
import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${basePackageName}.api.common.Pagination;
import ${basePackageName}.api.service.${baseClassName};
import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.service.biz.${bizServiceName};

/**
${classDoc}
*/
public class ${className} implements ${baseClassName} {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

    @Autowired
    private ${bizServiceName} ${bizServiceName?uncap_first};

    @Override
    public ${dtoName} save${dtoName}(${dtoName} ${dtoName?uncap_first}) {

        LOGGER.info("save${dtoName}: {}", ${dtoName?uncap_first});

        checkParams4Save${dtoName}(${dtoName?uncap_first});

        ${dtoName} saved${dtoName} = ${dtoName?uncap_first}BizService.save${dtoName}(${dtoName?uncap_first});

        return saved${dtoName};
    }

    @Override
    public Boolean remove${dtoName}(<#list pks as pk>${pk.type} ${pk.name}</#list>) {

        LOGGER.info("remove${dtoName}: {}", <#list pks as pk>${pk.name}</#list>);

        return ${dtoName?uncap_first}BizService.remove${dtoName}(<#list pks as pk>${pk.name}</#list>);
    }

    @Override
    public Boolean update${dtoName}(${dtoName} ${dtoName?uncap_first}) {

        LOGGER.info("update${dtoName}: {}", ${dtoName?uncap_first});

        checkParams4Update${dtoName}(${dtoName?uncap_first});

        return ${dtoName?uncap_first}BizService.update${dtoName}(${dtoName?uncap_first});
    }

    @Override
    public ${dtoName} get${dtoName}ById(<#list pks as pk>${pk.type} ${pk.name}</#list>) {

        ${dtoName} ${dtoName?uncap_first} = ${dtoName?uncap_first}BizService.get${dtoName}ById(<#list pks as pk>${pk.name}</#list>);

        return ${dtoName?uncap_first};
    }

    @Override
    public Pagination<${dtoName}> list${dtoName}ByOffset(Integer offset, Integer count) {
        return ${dtoName?uncap_first}BizService.list${dtoName}ByOffset(offset, count);
    }

    private void checkParams4Save${dtoName}(${dtoName} ${dtoName?uncap_first}) {
    }

    private void checkParams4Update${dtoName}(${dtoName} ${dtoName?uncap_first}) {
    }
}