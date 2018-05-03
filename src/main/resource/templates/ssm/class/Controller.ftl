<#include "../../lib/declare.ftl"/>
<#include "../../lib/data.ftl"/>
package ${package};

<#if pks?size gt 1>
import java.util.List;
import java.util.Map;
</#if>

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${basePackageName}.api.common.Pagination;
import ${basePackageName}.api.service.${serviceName};
import ${basePackageName}.api.dto.${dtoName};

import ${basePackageName}.web.basis.BaseController;
import ${basePackageName}.web.basis.ResponseVO;
import ${basePackageName}.web.converter.${beanConverter};
import ${basePackageName}.web.form.${formName};
import ${basePackageName}.web.vo.${voName};
${imports}

/**
 * ${classDoc}
 */
@RestController
@RequestMapping("/api/${dtoName?uncap_first}")
public class ${className} extends ${baseClassName} {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

    @Autowired
    private ${serviceName} ${serviceName?uncap_first};

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Pagination<?> getList(Integer offset, Integer count){

        Pagination<?> bean = ${serviceName?uncap_first}.listPaperByOffset(offset, count);

        return bean;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    <@compress_single_line>
    public ${voName} getById(${idParams}){
    </@compress_single_line>
        <@compress_single_line>
        return ${beanConverter}.toVO(${serviceName?uncap_first}.get${dtoName}ById(${idValues}));
        </@compress_single_line>
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseVO add(${formName} obj){

        ${dtoName} ${dtoName?uncap_first} = ${serviceName?uncap_first}.save${dtoName}(${beanConverter}.parseForm(obj));
        if (${dtoName?uncap_first} != null) {
            return ResponseVO.SUCCESS;
        } else {
            return ResponseVO.FAILED;
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    <@compress_single_line>
    public ResponseVO delete(${idParams}){
    </@compress_single_line>

        <@compress_single_line>
        if (${serviceName?uncap_first}.remove${dtoName}(${idValues})) {
        </@compress_single_line>
            return ResponseVO.SUCCESS;
        } else {
            return ResponseVO.FAILED;
        }

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseVO modify(@RequestBody ${formName} obj){

        if (${serviceName?uncap_first}.update${dtoName}(${beanConverter}.parseForm(obj))) {
            return ResponseVO.SUCCESS;
        } else {
            return ResponseVO.FAILED;
        }

    }

}