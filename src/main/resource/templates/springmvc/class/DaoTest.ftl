<#include "../../lib/declare.ftl"/>
package ${package};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.data.QueryBean;
import org.yannis.commons.web.request.http.GeneralQueryRequest;
import ${basePackageName}.domain.${domainName};
import ${basePackageName}.dao.${daoName};

import ${basePackageName}.dao.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ${className} extends BaseTest {

    @Autowired
    private ${daoName} ${daoName?uncap_first};
    
    @Test
    public void testFindById() throws Exception {
    }
    
    @Test
    public void testFindByPage() throws Exception {
    }
    
    @Test
    public void testSearch() throws Exception {
    }
    
    @Test
    public void testGetTotalRows() throws Exception {
    }
    
    @Test
    public void testGetTotalRows1() throws Exception {
    }
    
    @Test
    public void testRemove() throws Exception {
    }
    
    @Test
    public void testBatchRemove() throws Exception {
    }
    
    @Test
    public void testUpdate() throws Exception {
    }
    
    @Test
    public void testBatchUpdate() throws Exception {
    }

    @Test
    public void testSave() throws Exception {
    }

    @Test
    public void testBatchSave() throws Exception {
    }
}