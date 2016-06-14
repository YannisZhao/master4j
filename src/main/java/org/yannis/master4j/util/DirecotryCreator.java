package org.yannis.master4j.util;

import org.yannis.master4j.config.DirConfig;

/**
 * Created by yannis on 6/14/16.
 */
public class DirecotryCreator {
    public static void createDirs(DirConfig dirConfig) {
        System.out.println("start building directory...");
        FileUtils.mkdir(dirConfig.getBasePath());

        FileUtils.mkdir(dirConfig.getWebModulePath());
        /*FileUtils.mkdirs(dirConfig.getControllerModulePath());
        FileUtils.mkdirs(dirConfig.getVoModulerPath());
        FileUtils.mkdir(dirConfig.getImageModulePath());
        FileUtils.mkdir(dirConfig.getJsModulePath());
        FileUtils.mkdir(dirConfig.getCssModulePath());
        FileUtils.mkdir(dirConfig.getAddonModulePath());
        FileUtils.mkdir(dirConfig.getPageModulePath());*/

        FileUtils.mkdir(dirConfig.getApiModulePath());
        //FileUtils.mkdir(dirConfig.getServiceModulePath());

        FileUtils.mkdir(dirConfig.getImplModulePath());
        /*FileUtils.mkdir(dirConfig.getEntityModulerPath());
        FileUtils.mkdir(dirConfig.getServiceImplModulePath());
        FileUtils.mkdir(dirConfig.getDaoModulePath());*/

        FileUtils.mkdirs(dirConfig.getWebModulePath()+"/"+dirConfig.getSrcRelativePath());
        FileUtils.mkdirs(dirConfig.getWebModulePath()+"/"+dirConfig.getTestRelativePath());
        FileUtils.mkdirs(dirConfig.getWebModulePath()+"/"+dirConfig.getResourceRelativePath());

        FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getSrcRelativePath());
        FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getTestRelativePath());
        FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getResourceRelativePath());

        FileUtils.mkdirs(dirConfig.getImplModulePath()+"/"+dirConfig.getSrcRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath()+"/"+dirConfig.getTestRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath()+"/"+dirConfig.getResourceRelativePath());

    }
}
