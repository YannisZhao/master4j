package org.yannis.master4j.core.engine.springmvc;

import org.yannis.master4j.config.TemplateDir;

/**
 * Created by yannis on 6/13/16.
 */
public interface SpringmvcTemplateDir extends TemplateDir {

    String domainPath = springmvcBasePath + "/class/Domain.ftl";
    String controllerPath = springmvcBasePath + "/class/controller.ftl";
    String servicePath = springmvcBasePath + "/class/service.ftl";
    String daoPath = springmvcBasePath + "/class/dao.ftl";

}
