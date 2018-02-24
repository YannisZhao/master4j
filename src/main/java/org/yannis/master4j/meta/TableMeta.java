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

    private Index primary;

    private Index unique;

    private Index normal;

    private List<ColumnMeta> columnMetas;

    private String prefixName;

    private String comment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Index getPrimary() {
        return primary;
    }

    public TableMeta setPrimary(Index primary) {
        this.primary = primary;
        return this;
    }

    public Index getUnique() {
        return unique;
    }

    public TableMeta setUnique(Index unique) {
        this.unique = unique;
        return this;
    }

    public Index getNormal() {
        return normal;
    }

    public TableMeta setNormal(Index normal) {
        this.normal = normal;
        return this;
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

    public static class Index {

        public static final String PRIMARY = "primary";
        public static final String UNIQUE = "unique";
        public static final String NORMAL = "normal";

        private String type;
        private String name;
        private List<String> columns;

        public Index(String type, String name, List<String> columns) {
            this.type = type;
            this.name = name;
            this.columns = columns;
        }

        public String getType() {
            return type;
        }

        public Index setType(String type) {
            this.type = type;
            return this;
        }

        public String getName() {
            return name;
        }

        public Index setName(String name) {
            this.name = name;
            return this;
        }

        public List<String> getColumns() {
            return columns;
        }

        public Index setColumns(List<String> columns) {
            this.columns = columns;
            return this;
        }
    }

    @Override
    public String toString() {
        return String.format("tableName: %s, prefixName: %s, primaryKeys: %s, comment: %s, columnMetas: %s",
            tableName, prefixName, comment, columnMetas);
    }

}
