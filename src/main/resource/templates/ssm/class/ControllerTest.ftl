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
    public ${controllerName} ${controllerName?uncap_first};

    @Test
    public void testGetList() throws Exception {

    }

    @Test
    public void testFind() throws Exception {
    }

    @Test
    public void testAdd() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
    }

    @Test
    public void testBatchDelete() throws Exception {
    }

    @Test
    public void testModify() throws Exception {
    }
}
