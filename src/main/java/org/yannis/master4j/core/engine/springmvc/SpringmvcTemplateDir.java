package org.yannis.master4j.core.engine.springmvc;

import org.yannis.master4j.config.TemplateDir;

/**
 * Created by yannis on 6/13/16.
 */
public interface SpringmvcTemplateDir extends TemplateDir {

    String controllerPath = springmvcBasePath + "/class/controller.ms";
    String servicePath = springmvcBasePath + "/class/service.ms";
    String daoPath = springmvcBasePath + "/class/dao.ms";

}
