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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.request.data.json.JsonQueryAdapter;
import org.yannis.commons.web.response.enums.Message;
import org.yannis.commons.web.response.vo.ResponseMsg;

import ${basePackageName}.web.converter.${beanConverter};
import ${basePackageName}.web.basis.BaseController;
import ${basePackageName}.api.service.${serviceName};
import ${basePackageName}.web.form.${formName};
import ${basePackageName}.web.vo.${voName};
${imports}

/**
${classDoc}
*/
@RestController
@RequestMapping("/${dtoName?uncap_first}")
public class ${className} extends ${baseClassName} {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

    @Autowired
    private ${serviceName} ${serviceName?uncap_first};

    @RequestMapping("list")
    public PageBean<?> getList(JsonQueryAdapter adapter){

        PageBean<?> bean = ${serviceName?uncap_first}.findByPage(adapter.getRequest());

        return bean;
    }

    @RequestMapping("find")
    <@compress_single_line>
    public ${voName} find(${idParams}){
    </@compress_single_line>
        <@compress_single_line>
        return ${beanConverter}.toVO(${serviceName?uncap_first}.findById(${idValues}));
        </@compress_single_line>
    }

    @RequestMapping("add")
    public ResponseMsg add(${formName} obj){

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.save(${beanConverter}.parseForm(obj))){
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

        return responseVO;

    }

    @RequestMapping("delete")
    <@compress_single_line>
    public ResponseMsg delete(${idParams}){
    </@compress_single_line>

        ResponseMsg responseVO = new ResponseMsg();

        <@compress_single_line>
        if(${serviceName?uncap_first}.remove(${idValues})){
        </@compress_single_line>
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

        return responseVO;
    }

    @RequestMapping("batch-delete")
    <@compress_single_line>
    public ResponseMsg batchDelete(@RequestBody ${batchRemoveParams}){
    </@compress_single_line>

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.batchRemove(<#if pks?size == 1>ids<#else>pks</#if>) == <#if pks?size == 1>ids.length<#else>pks.size()</#if>){
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

        return responseVO;
    }

    @RequestMapping("modify")
    public ResponseMsg modify(${formName} obj){

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.update(${beanConverter}.parseForm(obj))){
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

        return responseVO;
    }

}