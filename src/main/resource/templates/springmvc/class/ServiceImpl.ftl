<#include "../../lib/data.ftl"/>
package ${package};

${imports}
import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;

import ${basePackageName}.api.service.${baseClassName};
import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.domain.${domainName};
import ${basePackageName}.dao.${daoName};
import ${basePackageName}.converter.${converterName};

/**
${classDoc}
*/
public class ${className} implements ${baseClassName} {

    @Autowired
    private ${daoName} ${daoName?uncap_first};

    <@compress_single_line>
    public ${dtoName} findById(${idParams}) {
    </@compress_single_line>
        <@compress_single_line>
        ${domainName} entity = ${daoName?uncap_first}.findById(${idValues});
        </@compress_single_line>
        return ${converterName}.entity2DTO(entity);
    }

    @Override
    public PageBean<${dtoName}> findByPage(Paginator paginator) {
        PageBean<${dtoName}> responseVO = new PageBean<${dtoName}>();
        responseVO.setTotal(${daoName?uncap_first}.getTotalRows());
        List<${domainName}> ${domainName?uncap_first}List = ${daoName?uncap_first}.findByPage(paginator);
        responseVO.setData(${converterName}.entity2DTO(${domainName?uncap_first}List));
        return responseVO;
    }

    @Override
    public PageBean<?> search(GeneralQueryRequest request) {
        PageBean pageBean = new PageBean();

        pageBean.setTotal(${daoName?uncap_first}.getTotalRows(request));
        pageBean.setData(${daoName?uncap_first}.search(request));

        return pageBean;
    }

    @Override
    public boolean save(${dtoName} obj) {
       return ${daoName?uncap_first}.save(${converterName}.dto2Entity(obj));
    }

    @Override
    public int batchSave(List<${dtoName}> objs) {
        return ${daoName?uncap_first}.batchSave(${converterName}.dto2Entity(objs));
    }

    @Override
    <@compress_single_line>
    public boolean remove(${idParams}) {
    </@compress_single_line>
        <@compress_single_line>
        return ${daoName?uncap_first}.remove(${idValues});
        </@compress_single_line>
    }

    @Override
    <@compress_single_line>
    public int batchRemove(${batchRemoveParams}) {
    </@compress_single_line>
       return ${daoName?uncap_first}.batchRemove(<#if pks?size == 1>ids<#else>pks</#if>);
    }

    @Override
    public boolean update(${dtoName} obj) {
        return ${daoName?uncap_first}.update(${converterName}.dto2Entity(obj));
    }

    @Override
    public int batchUpdate(List<${dtoName}> objs) {
       return ${daoName?uncap_first}.batchUpdate(${converterName}.dto2Entity(objs));
    }

}