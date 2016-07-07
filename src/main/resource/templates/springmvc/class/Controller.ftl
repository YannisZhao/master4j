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
    public PageBean<?> getList(RequestParams params, Paginator paginator){
        PageBean<?> bean = ${serviceName?uncap_first}.findByPage(SqlFactorUtils.parse(params.getParams()), paginator);
        return bean;
    }

    @RequestMapping("find")
    public ${domainName} find(String id){
        return ${serviceName?uncap_first}.findById(id);
    }

    @RequestMapping("add")
    public ResponseMsg add(${domainName} obj){

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.save(obj)){
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

        return responseVO;

    }

    @RequestMapping("delete")
    public ResponseMsg delete(@RequestBody List<${domainName}> objs){

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.batchRemove(objs) == objs.size()){
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

            return responseVO;
        }

    @RequestMapping("modify")
    public ResponseMsg modify(${domainName} obj){

        ResponseMsg responseVO = new ResponseMsg();

        if(${serviceName?uncap_first}.update(obj)){
            responseVO.setStatus(true);
            responseVO.setMessage(Message.OPT_SUCCESSED);
        }else{
            responseVO.setStatus(false);
            responseVO.setMessage(Message.OPT_FAILED);
        }

        return responseVO;
    }

}