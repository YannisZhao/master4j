<#include "../../lib/declare.ftl"/>
package ${package};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ${basePackageName}.api.service.${domainName}Service;

public class ${className} extends BaseTest  {

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
