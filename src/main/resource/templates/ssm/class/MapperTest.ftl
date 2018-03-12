<#include "../../lib/declare.ftl"/>
package ${package};

import org.springframework.beans.factory.annotation.Autowired;
import ${basePackageName}.entity.${entityName};
import ${basePackageName}.mapper.${mapperName};

import ${basePackageName}.BaseTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.After;
import org.junit.Test;

public class ${className} extends BaseTest {

    @Autowired
    private ${mapperName} ${mapperName?uncap_first};

    @After
    public void after() throws Exception {
        clearTable("${tableName}");
    }

    @Test
    public void insert() throws Exception {
        ${entityName} entity = mock${dtoName}();
        Long id = ${mapperName?uncap_first}.insert(entity);
        assertTrue(id > 0);
    }

    @Test
    public void deleteById() throws Exception {
        ${entityName} entity = mock${dtoName}();
        Long id = ${mapperName?uncap_first}.insert(entity);
        int affectedRows = ${mapperName?uncap_first}.deleteById(id);
        assertEquals(1, affectedRows);
    }

    @Test
    public void updateById() throws Exception {
        ${entityName} entity = mock${dtoName}();
        Long id = ${mapperName?uncap_first}.insert(entity);

        <#list fields as field><#if !field.primary>${field.type} oldValue = entity.get${field.name?cap_first}()<#break/>()</#if></#list>;

        entity.set<#list fields as field><#if !field.primary>${field.name?cap_first}<#break/></#if></#list>(entity.get<#list fields as field><#if !field.primary>${field.name?cap_first}<#break/></#if></#list>() + 1);
        int affectedRows = ${mapperName?uncap_first}.updateById(entity);
        assertEquals(1, affectedRows);

        ${entityName} newEntity = ${mapperName?uncap_first}.selectById(id);
        assertNotEquals(oldValue, newEntity.get<#list fields as field><#if !field.primary>${field.name?cap_first}()<#break/></#if></#list>);
    }

    @Test
    public void selectById() throws Exception {
        ${entityName} entity = mock${dtoName}();
        Long id = ${mapperName?uncap_first}.insert(entity);
        ${entityName} dbEntity = ${mapperName?uncap_first}.selectById(id);
        assertEquals(<#list fields as field><#if !field.primary>entity.get${field.name?cap_first}(), dbEntity.get${field.name?cap_first}()<#break/></#if></#list>);
    }

    @Test
    public void selectByIds() throws Exception {
        ${entityName} entity = mock${dtoName}();
        Long id1 = ${mapperName?uncap_first}.insert(entity);
        Long id2 = ${mapperName?uncap_first}.insert(entity);

        List<${entityName}> ${dtoName?uncap_first}s = ${mapperName?uncap_first}.selectByIds(Arrays.asList(id1, id2));
        assertTrue(2 == ${dtoName?uncap_first}s.size());
    }

    @Test
    public void selectIdsByOffset() throws Exception {

    }

    @Test
    public void count() throws Exception {

    }

    private ${entityName} mock${dtoName}() {
       ${mockBeanScript}
    }
}