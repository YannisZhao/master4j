<#include "../../lib/declare.ftl"/>
package ${package};

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class ${className} extends BaseTest  {

    @Autowired
    public ${domainName}Controller ${domainName?uncap_first}Controller;

    @Test
    public void testGetList() throws Exception {

        String request = "{page:1, rows:10, sorts:\"\", queryBeanList:[]}";

        this.mockMvc
        .perform(post("/${domainName?uncap_first}/list")
        .accept(MediaType.APPLICATION_JSON)
        .param("actual", request)
        .param("expect", request))
        .andDo(print())
        .andExpect(status().isOk());

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
