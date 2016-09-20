package ${package};

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.request.data.json.JsonQueryAdapter;
import org.yannis.commons.web.response.enums.Message;
import org.yannis.commons.web.response.vo.ResponseMsg;

${imports}
import ${basePackageName}.web.converter.${beanConverter};
import ${basePackageName}.web.base.${baseClassName};
import ${basePackageName}.api.service.${serviceName};

/**
${classDoc}
*/
@RestController
@RequestMapping("/${domainName?uncap_first}")
public class ${className} extends ${baseClassName} {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

    @Autowired
    public ${serviceName} ${serviceName?uncap_first};

    @RequestMapping("list")
    public PageBean<?> getList(JsonQueryAdapter adapter){

        LOGGER.info("User[{}] request {} with paging conditions: {}", "Yannis", "/application/list", JSON.toJSONString(adapter.getRequest(), SerializerFeature.WriteMapNullValue));

        PageBean<?> bean = ${serviceName?uncap_first}.findByPage(adapter.getRequest());

        return bean;
    }

    @RequestMapping("find")
    public ${voName} find(String id){
        return ${beanConverter}.toVO(${serviceName?uncap_first}.findById(id));
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
    public ResponseMsg delete(String id){

        ResponseMsg responseVO = new ResponseMsg();

        if(applicationService.remove(id)){
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

        return responseVO;
    }

    @RequestMapping("batch-delete")
    public ResponseMsg batchDelete(@RequestBody List<String> ids){

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.batchRemove(ids) == ids.size()){
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