package org.yannis.master4j.meta;

public class ColumnMeta {

    private String columnName;

    private String columnType;

    private int columnSize;

    private boolean isAutoIncrement;

    private boolean isNullable;

    private String comment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return String.format("columnName: %s, columnType: %s, columnSize: %s, isAutoIncrement: %s, isNullable: %s, comment: %s",
                columnName, columnType, columnSize, isAutoIncrement, isNullable, comment);
    }
}
