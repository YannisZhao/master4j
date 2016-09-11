package ${package};

${imports}

import org.yannis.test.base.${baseClassName};
import org.yannis.test.service.${serviceName};

/**
${classDoc}
*/
@RestController
@RequestMapping("/${domainName?uncap_first}")
public class ${className} extends ${baseClassName} {

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
    public ResponseMsg batchDelete(@RequestBody List<${formName}> objs){

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.batchRemove(${beanConverter}.parseForm(objs)) == objs.size()){
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