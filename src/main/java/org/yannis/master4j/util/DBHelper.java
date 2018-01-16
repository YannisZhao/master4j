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
package org.yannis.master4j.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.config.DBConfig;
import org.yannis.master4j.meta.ColumnMeta;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.meta.TableMeta;

public class DBHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBHelper.class);

    private DBConfig dbConfig;

    private Connection connection;

    public DBHelper(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Try to connect to database server: {}:{}, charset: {}", dbConfig.getIp(), dbConfig.getPort(),
                dbConfig.getCharset());
        }
        connection = createConnection(dbConfig);
    }

    public DatabaseMeta getDatabaseMeta() {
        DatabaseMeta meta = new DatabaseMeta();
        meta.setTableMetaList(getTableMetas());
        close(this.connection);
        return meta;
    }

    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot close connection", e);
            }
        }
    }

    private Connection createConnection(DBConfig dbConfig) {
        String url = "";
        Connection connection = null;
        try {
            Class.forName(dbConfig.getDriverPackage());
            url = dbConfig.getProtocalPrefix() + dbConfig.getIp() + ":" + dbConfig.getPort() + "/" + dbConfig
                .getDatabase() + "?characterEncoding=" + dbConfig.getCharset();
            connection = DriverManager.getConnection(url, dbConfig.getUsername(), dbConfig.getPassword());
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot resolve driver class from {}, Class not found", dbConfig.getDriverPackage());
        } catch (SQLException e) {
            LOGGER.error("Cannot establish connection to database:{}", url);
        }
        return connection;
    }

    public List<TableMeta> getTableMetas() {
        List<TableMeta> tableMetas = new ArrayList<>();
        List<String> tables = getTables();
        for (String table : tables) {
            tableMetas.add(getTableMeta(table));
        }
        return tableMetas;
    }

    private TableMeta getTableMeta(String table) {
        TableMeta meta = new TableMeta();

        String tableName = table;
        final String tablePrefix = dbConfig.getTablePrefix();
        if (tablePrefix != null && !"".equals(tablePrefix)) {
            if (table.startsWith(tablePrefix)) {
                tableName = table.substring(table.indexOf(tablePrefix) + tablePrefix.length());
            }
        }

        meta.setTableName(tableName);
        List<String> tablePrimaryKeys = getTablePrimaryKeys(table);
        meta.setColumnMetas(getColumnMetas(table, tablePrimaryKeys));
        meta.setComment(getTableComment(table));

        return meta;
    }

    public List<String> getTables() {
        List<String> tables = new ArrayList<>();

        DatabaseMetaData dbMetaData = null;
        ResultSet resultSet = null;
        try {
            dbMetaData = this.connection.getMetaData();
            tables = new ArrayList<>();
            resultSet = dbMetaData.getTables(null, "%", "", new String[]{"TABLE"});
            while (resultSet.next()) {
                String table = resultSet.getString("TABLE_NAME");
                if (isTableEnabled(table)) {
                    tables.add(table);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
        }

        return tables;
    }

    private boolean isTableEnabled(String table) {
        List<String> excludeTables = dbConfig.getExcludeTables();
        if (excludeTables != null && excludeTables.size() > 0) {
            if (excludeTables.contains(table)) {
                return false;
            }
        }

        String tablePrefix = dbConfig.getTablePrefix();
        if (tablePrefix != null && !table.startsWith(tablePrefix)) {
            return false;
        }

        List<String> includeTables = dbConfig.getIncludeTables();
        if (includeTables != null && includeTables.size() > 0) {
            for (String includeTable : includeTables) {
                if ("*".equalsIgnoreCase(includeTable) || table.equalsIgnoreCase(includeTable)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private List<String> getTablePrimaryKeys(String table) {
        List<String> primaryKeys = null;

        ResultSet resultSet = null;

        try {

            DatabaseMetaData dbMetaData = connection.getMetaData();

            primaryKeys = new ArrayList<>();

            resultSet = dbMetaData.getPrimaryKeys(null, "%", table);
            while (resultSet.next()) {
                primaryKeys.add(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
        }

        return primaryKeys;
    }

    private List<ColumnMeta> getColumnMetas(String table, List<String> tablePrimaryKeys) {

        List<ColumnMeta> columnMetas = new ArrayList<>();

        ResultSet resultSet = null;

        try {

            DatabaseMetaData dbMetaData = connection.getMetaData();

            resultSet = dbMetaData.getColumns(null, "%", table, "%");
            while (resultSet.next()) {

                String columnName = resultSet.getString("COLUMN_NAME");
                String columnType = resultSet.getString("TYPE_NAME");
                int columnSize = resultSet.getInt("COLUMN_SIZE");
                int isNullable = resultSet.getInt("NULLABLE");
                boolean isAutoIncrement = resultSet.getBoolean("IS_AUTOINCREMENT");
                String comment = resultSet.getString("REMARKS");
                String defaultValue = null;

                ColumnMeta columnMeta = new ColumnMeta();
                columnMeta.setColumnName(columnName);
                columnMeta.setColumnType(SqlTypeMapper.getType(columnType));
                columnMeta.setDefaultValue(defaultValue);
                columnMeta.setAutoIncrement(isAutoIncrement);
                columnMeta.setColumnSize(columnSize);
                columnMeta.setNullable(isNullable > 0);
                columnMeta.setComment(comment);

                for (String pk : tablePrimaryKeys) {
                    if (columnName.equals(pk)) {
                        columnMeta.setPrimary(true);
                        break;
                    }
                }

                columnMetas.add(columnMeta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
        }

        return columnMetas;
    }

    private String getTableComment(String table) {
        ResultSet resultSet = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                "select table_comment from information_schema.tables where table_schema=? and TABLE_name =?");
            stmt.setString(1, dbConfig.getDatabase());
            stmt.setString(2, table);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
        }
        return null;
    }

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ResultSet", e);
            }
        }
    }
}
