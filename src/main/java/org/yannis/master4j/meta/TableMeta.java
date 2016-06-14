package org.yannis.master4j.meta;

import java.util.List;

public class TableMeta {

    private String tableName;

    private List<ColumnMeta> columnMetas;

    private List<String> primaryKeys;

    private String prefixName;

    private String comment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnMeta> getColumnMetas() {
        return columnMetas;
    }

    public void setColumnMetas(List<ColumnMeta> columnMetas) {
        this.columnMetas = columnMetas;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ColumnMeta getColumnMeta(String name) {

        for (ColumnMeta meta : columnMetas) {
            if (meta.getColumnName().equals(name)) {
                return meta;
            }
        }

        return null;
    }

    public boolean isHasAutoIncrementColumn() {

        for (ColumnMeta meta : columnMetas) {
            if (meta.isAutoIncrement()) {
                return true;
            }
        }

        return false;
    }

    public ColumnMeta getAutoIncrementColumn() {

        for (ColumnMeta meta : columnMetas) {
            if (meta.isAutoIncrement()) {
                return meta;
            }
        }

        return null;
    }

    public boolean isInPrimaryKeys(String name) {

        if (primaryKeys.contains(name)) {
            return true;
        }

        return false;
    }

}
