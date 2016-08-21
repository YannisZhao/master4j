package ${package};

${imports}

import org.yannis.test.service.${baseClassName};

/**
${classDoc}
*/
public class ${className} implements ${baseClassName} {

    @Autowired
    private ${daoName} ${daoName?uncap_first};

    public ${domainName} findById(String id) {
        return ${daoName?uncap_first}.findById(id);
    }

    public boolean delete(String[] ids) {
        return ${daoName?uncap_first}.delete(ids);
    }

    @Override
    public boolean save(WaybillMaster obj) {
       return ${daoName?uncap_first}.save(obj);
    }

    @Override
    public int batchSave(List<${domainName}> objs) {
        return ${daoName?uncap_first}.batchSave(objs);
    }

    @Override
    public boolean remove(${domainName} obj) {
       return ${daoName?uncap_first}.remove(obj);
    }

    @Override
    public int batchRemove(List<${domainName}> objs) {
       return ${daoName?uncap_first}.batchRemove(objs);
    }

    @Override
    public boolean update(WaybillMaster obj) {
        return ${daoName?uncap_first}.update(obj);
    }

    @Override
    public int batchUpdate(List<${domainName}> objs) {
       return ${daoName?uncap_first}.batchUpdate(objs);
    }

    @Override
    public WaybillMaster findById(WaybillMaster objs) {
       return ${daoName?uncap_first}.findById(objs);
    }

    @Override
    public List<${domainName}> findAll(SqlParamWrapper[] params) {
       return ${daoName?uncap_first}.findAll(params);
    }

    @Override
    public PageBean<${domainName}> findByPage(SqlParamWrapper[] params, Paginator paginator) {
        PageBean<${domainName}> responseVO = new PageBean<${domainName}>();
        responseVO.setTotal(${daoName?uncap_first}.getTotalRows(params));
        responseVO.setRows(${daoName?uncap_first}.findByPage(params,paginator));
        return responseVO;
    }

}