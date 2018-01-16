/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Also with any question can email zhaoyjun0222@gmail.com
 */
package org.yannis.master4j.meta;

import java.util.List;

public class TableMeta {

    private String tableName;

    private List<ColumnMeta> columnMetas;

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

    @Override
    public String toString() {
        return String.format("tableName: %s, prefixName: %s, primaryKeys: %s, comment: %s, columnMetas: %s",
            tableName, prefixName, comment, columnMetas);
    }

}
