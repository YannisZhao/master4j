package ${package};

${imports}
import java.util.List;

import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;

import ${basePackageName}.dto.${dtoName};

/**
${classDoc}
*/
public interface ${className} {

    ${dtoName} findById(String id);

    List<${dtoName}> findAll(GeneralQueryRequest request);

    PageBean<?> findByPage(Paginator paginator);

    boolean save(${dtoName} obj);
    
    int batchSave(List<${dtoName}> objs);
    
    boolean remove(String id);

    int batchRemove(List<${dtoName}> objs);

    boolean update(${dtoName} obj);

    int batchUpdate(List<${dtoName}> objs);

}