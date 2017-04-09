<#include "../../lib/declare.ftl"/>
<#assign serviceObj>${domainName?uncap_first}Service</#assign>
package ${package};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;
import ${basePackageName}.api.dto.${domainName}DTO;
import ${basePackageName}.service.BaseTest;
import ${basePackageName}.api.service.${domainName}Service;
${imports}

import java.util.List;

public class ${className} extends BaseTest {

    @Autowired
    private ${domainName}Service ${serviceObj};

    @Test
    public void findById() {
    }

    public void findByPage() {

    }

    public void search() {

    }

    public void save() {

    }

    public void batchSave() {

    }

    public void remove() {

    }

    public void batchRemove() {

    }

    public void update() {

    }

    public void batchUpdate() {

    }

}
