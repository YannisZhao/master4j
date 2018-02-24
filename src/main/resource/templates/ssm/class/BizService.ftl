<#include "../../lib/declare.ftl"/>
<#include "../../lib/data.ftl"/>
package ${package};

${imports}
import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${basePackageName}.api.common.Pagination;
import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.entity.${domainName};
import ${basePackageName}.converter.${convertName};
import ${basePackageName}.cache.${cacheServiceName};
import ${basePackageName}.mapper.${mapperName};

/**
${classDoc}
*/
@Service
public class ${className} {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

    @Autowired
    private ${mapperName} ${mapperName?uncap_first};

    @Autowired
    private ${cacheServiceName} ${cacheServiceName?uncap_first};

    public ${dtoName} save${dtoName}(${dtoName} ${dtoName?uncap_first}) {

        ${domainName} ${domainName?uncap_first} = ${convertName}.dto2Entity(${dtoName?uncap_first});

        Long ${dtoName?uncap_first}Id = ${mapperName?uncap_first}.insert(${domainName?uncap_first});
        ${domainName} db${domainName} = ${mapperName?uncap_first}.selectById(${dtoName?uncap_first}Id);
        ${dtoName} db${dtoName} = ${convertName}.entity2DTO(db${domainName});

        ${cacheServiceName?uncap_first}.save${dtoName}(db${dtoName});

        return db${dtoName};
    }

    public Boolean remove${dtoName}(<#list pks as pk>${pk.type} ${dtoName?uncap_first}Id</#list>) {

        Integer affectedRows = ${mapperName?uncap_first}.deleteById(${dtoName?uncap_first}Id);
        if (1 == affectedRows) {
            ${cacheServiceName?uncap_first}.remove${dtoName}(${dtoName?uncap_first}Id);
            return true;
        }
        return false;
    }

    public Boolean update${dtoName}(${dtoName} ${dtoName?uncap_first}) {

        ${domainName} ${domainName?uncap_first} = ${convertName}.dto2Entity(${dtoName?uncap_first});
        Integer affectedRows = ${mapperName?uncap_first}.updateById(${domainName?uncap_first});

        if (1 == affectedRows) {
            ${cacheServiceName?uncap_first}.remove${dtoName}(${dtoName?uncap_first}.getId());
            return true;
        }

        return false;
    }

    public ${dtoName} get${dtoName}ById(<#list pks as pk>${pk.type} ${dtoName?uncap_first}Id</#list>) {

        ${dtoName} ${dtoName?uncap_first} = ${cacheServiceName?uncap_first}.get${dtoName}(${dtoName?uncap_first}Id);
        if (${dtoName?uncap_first} != null) {
            return ${dtoName?uncap_first};
        }

        ${domainName} ${domainName?uncap_first} = ${mapperName?uncap_first}.selectById(${dtoName?uncap_first}Id);
        if (${domainName?uncap_first} == null) {
            return null;
        }

        ${dtoName?uncap_first} = ${convertName}.entity2DTO(${domainName?uncap_first});
        ${cacheServiceName?uncap_first}.save${dtoName}(${dtoName?uncap_first});
        return ${dtoName?uncap_first};
    }

    public Pagination<${dtoName}> list${dtoName}ByOffset(Integer offset, Integer count) {
        Pagination<Long> ${dtoName?uncap_first}IdPage = get${dtoName}IdsWithOffset(offset, count);
        long total = ${dtoName?uncap_first}IdPage.getTotal();
        if (total <= 0) {
            return new Pagination<>(0, Collections.emptyList());
        }

        List<Long> ${dtoName?uncap_first}Ids = ${dtoName?uncap_first}IdPage.getData();
        Map<Long, ${dtoName}> ${dtoName?uncap_first}Map = ${cacheServiceName?uncap_first}.batchGet${dtoName}s(${dtoName?uncap_first}Ids);
        fillNonFetched${dtoName}(${dtoName?uncap_first}Map);

        return new Pagination<>(${dtoName?uncap_first}IdPage.getTotal(), returnSortedList(${dtoName?uncap_first}Ids, ${dtoName?uncap_first}Map));
    }

    private Pagination<Long> get${dtoName}IdsWithOffset(Integer offset, Integer count) {
        return null;
    }

    private void fillNonFetched${dtoName}(Map<Long, ${dtoName}> ${dtoName?uncap_first}Map) {
        final List<Long> missed${dtoName}Ids = ${dtoName?uncap_first}Map.keySet().stream()
            .filter(${dtoName?uncap_first}Id -> ${dtoName?uncap_first}Map.get(${dtoName?uncap_first}Id) == null).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(missed${dtoName}Ids)) {
            return;
        }

        List<${domainName}> ${dtoName?uncap_first}Entities = ${mapperName?uncap_first}.selectByIds(missed${dtoName}Ids);
        if (CollectionUtils.isEmpty(${dtoName?uncap_first}Entities)) {
          return;
        }

        List<${dtoName}> ${dtoName?uncap_first}s = ${convertName}.entity2DTO(${dtoName?uncap_first}Entities);
        ${dtoName?uncap_first}s.stream().forEach(${dtoName?uncap_first} -> ${dtoName?uncap_first}Map.put(${dtoName?uncap_first}.getId(), ${dtoName?uncap_first}));

        ${cacheServiceName?uncap_first}.save${dtoName}s(${dtoName?uncap_first}s);
    }

    private List<${dtoName}> returnSortedList(List<Long> ${dtoName?uncap_first}Ids, Map<Long, ${dtoName}> ${dtoName?uncap_first}Map) {
        return ${dtoName?uncap_first}Ids.stream().map(${dtoName?uncap_first}Map::get).collect(Collectors.toList());
    }
}