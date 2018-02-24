package org.yannis.master4j.model;

import java.util.List;
import org.yannis.master4j.meta.TableMeta;

/**
 * @author Yannis Zhao
 * @date 2018/1/21
 * @since 1.0
 */
public class BeanInfo {

    private TableMeta tableMeta;
    private List<Field> fieldList;
    private String controllerName;
    private String serviceName;
    private String serviceImplName;
    private String testServiceName;
    private String bizServiceName;
    private String cacheServiceName;
    private String daoName;
    private String daoImplName;
    private String testDaoName;
    private String mapperName;
    private String mapperImplName;
    private String testMapperName;
    private String entityName;
    private String dtoName;
    private String voName;
    private String formName;
    private String voConverterName;
    private String entityConverterName;

    public TableMeta getTableMeta() {
        return tableMeta;
    }

    public BeanInfo setTableMeta(TableMeta tableMeta) {
        this.tableMeta = tableMeta;
        return this;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public BeanInfo setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
        return this;
    }

    public String getControllerName() {
        return controllerName;
    }

    public BeanInfo setControllerName(String controllerName) {
        this.controllerName = controllerName;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public BeanInfo setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public BeanInfo setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
        return this;
    }

    public String getTestServiceName() {
        return testServiceName;
    }

    public BeanInfo setTestServiceName(String testServiceName) {
        this.testServiceName = testServiceName;
        return this;
    }

    public String getBizServiceName() {
        return bizServiceName;
    }

    public BeanInfo setBizServiceName(String bizServiceName) {
        this.bizServiceName = bizServiceName;
        return this;
    }

    public String getCacheServiceName() {
        return cacheServiceName;
    }

    public BeanInfo setCacheServiceName(String cacheServiceName) {
        this.cacheServiceName = cacheServiceName;
        return this;
    }

    public String getDaoName() {
        return daoName;
    }

    public BeanInfo setDaoName(String daoName) {
        this.daoName = daoName;
        return this;
    }

    public String getDaoImplName() {
        return daoImplName;
    }

    public BeanInfo setDaoImplName(String daoImplName) {
        this.daoImplName = daoImplName;
        return this;
    }

    public String getTestDaoName() {
        return testDaoName;
    }

    public BeanInfo setTestDaoName(String testDaoName) {
        this.testDaoName = testDaoName;
        return this;
    }

    public String getMapperName() {
        return mapperName;
    }

    public BeanInfo setMapperName(String mapperName) {
        this.mapperName = mapperName;
        return this;
    }

    public String getMapperImplName() {
        return mapperImplName;
    }

    public BeanInfo setMapperImplName(String mapperImplName) {
        this.mapperImplName = mapperImplName;
        return this;
    }

    public String getTestMapperName() {
        return testMapperName;
    }

    public BeanInfo setTestMapperName(String testMapperName) {
        this.testMapperName = testMapperName;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public BeanInfo setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public String getDtoName() {
        return dtoName;
    }

    public BeanInfo setDtoName(String dtoName) {
        this.dtoName = dtoName;
        return this;
    }

    public String getVoName() {
        return voName;
    }

    public BeanInfo setVoName(String voName) {
        this.voName = voName;
        return this;
    }

    public String getFormName() {
        return formName;
    }

    public BeanInfo setFormName(String formName) {
        this.formName = formName;
        return this;
    }

    public String getVoConverterName() {
        return voConverterName;
    }

    public BeanInfo setVoConverterName(String voConverterName) {
        this.voConverterName = voConverterName;
        return this;
    }

    public String getEntityConverterName() {
        return entityConverterName;
    }

    public BeanInfo setEntityConverterName(String entityConverterName) {
        this.entityConverterName = entityConverterName;
        return this;
    }
}
