package ${package};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ${basePackageName}.api.service.ApplicationService;

public class ${className} {

    @Autowired
    public ${domainName}Service ${domainName?uncap_first}Service;

    @Test
    public void testGetList(){
    }

    @Test
    public void testFind(){
    }

    @Test
    public void testAdd(){
    }

    @Test
    public void testDelete(){
    }

    @Test
    public void testBatchDelete(){
    }

    @Test
    public void testModify(){
    }
}
