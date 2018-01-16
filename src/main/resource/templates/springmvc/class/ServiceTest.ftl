<#include "../../lib/declare.ftl"/>
package ${package};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yannis.commons.web.page.PageBean;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.http.GeneralQueryRequest;
import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.service.BaseTest;
import ${basePackageName}.api.service.${serviceName};
${imports}

import java.util.List;

public class ${className} extends BaseTest {

    @Autowired
    private ${serviceName} ${serviceName?uncap_first};

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
