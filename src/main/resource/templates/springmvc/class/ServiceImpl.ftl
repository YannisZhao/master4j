package ${package};

${imports}
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;

import ${basePackageName}.api.service.${baseClassName};
import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.dao.${daoName};

/**
${classDoc}
*/
public class ${className} implements ${baseClassName} {

    @Autowired
    private ${daoName} ${daoName?uncap_first};

    public ${dtoName} findById(String id) {
        return ${daoName?uncap_first}.findById(id);
    }

    @Override
    public List<${dtoName}> findAll(SqlParamWrapper[] params) {
        return ${daoName?uncap_first}.findAll(params);
    }

    @Override
    public PageBean<${dtoName}> findByPage(SqlParamWrapper[] params, Paginator paginator) {
        PageBean<${dtoName}> responseVO = new PageBean<${dtoName}>();
        responseVO.setTotal(${daoName?uncap_first}.getTotalRows(params));
        responseVO.setRows(${daoName?uncap_first}.findByPage(params,paginator));
        return responseVO;
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
    public boolean remove(String id) {
       return ${daoName?uncap_first}.remove(id);
    }

    @Override
    public int batchRemove(String[] ids) {
       return ${daoName?uncap_first}.batchRemove(ids);
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