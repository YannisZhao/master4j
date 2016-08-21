package ${package};

${imports}

/**
${classDoc}
*/
public interface ${className} {

    ${domainName} findById(String id);

    boolean delete(String[] ids);

    boolean save(WaybillMaster obj);

    int batchSave(List<${domainName}> objs);

    boolean remove(${domainName} obj);

    int batchRemove(List<${domainName}> objs);

    boolean update(WaybillMaster obj);

    int batchUpdate(List<${domainName}> objs);

    WaybillMaster findById(WaybillMaster objs);

    List<${domainName}> findAll(SqlParamWrapper[] params);

    PageBean<${domainName}> findByPage(SqlParamWrapper[] params, Paginator paginator);

}