<#include "../../lib/declare.ftl"/>
package ${package};

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ${basePackageName}.api.common.Pagination;
import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.BaseTest;
import ${basePackageName}.api.service.${serviceName};
${imports}

import java.util.List;

public class ${className} extends BaseTest {

    @Autowired
    private ${serviceName} ${serviceName?uncap_first};

    @After
    public void after() throws Exception {
        clearTable("${tableName}");
        // TODO: clear cache here
    }

    @Test
    public void save${dtoName}() {
    }

    @Test
    public void remove${dtoName}() {

    }

    @Test
    public void get${dtoName}ById() {

    }

    @Test
    public void get${dtoName}ByIdsAsMap() {

    }

    @Test
    public void list${dtoName}ByOffset() {

    }

}
