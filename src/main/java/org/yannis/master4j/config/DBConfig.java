package org.yannis.master4j.config;

import java.util.List;

/**
 * Created by yannis on 6/13/16.
 */
public class DBConfig {

    private String ip = "localhost";

    private int port = 3306;

    private String database;

    private String username = "root";

    private String password = "";

    private String charset = "UTF8";

    private List<String> includeTables;

    private List<String> excludeTables;

    private String tablePrefix;

    private String driverPackage = "com.mysql.jdbc.Driver";

    private String protocalPrefix = "jdbc:mysql://";

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public List<String> getIncludeTables() {
        return includeTables;
    }

    public void setIncludeTables(List<String> includeTables) {
        this.includeTables = includeTables;
    }

    public List<String> getExcludeTables() {
        return excludeTables;
    }

    public void setExcludeTables(List<String> excludeTables) {
        this.excludeTables = excludeTables;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getDriverPackage() {
        return driverPackage;
    }

    public void setDriverPackage(String driverPackage) {
        this.driverPackage = driverPackage;
    }

    public String getProtocalPrefix() {
        return protocalPrefix;
    }

    public void setProtocalPrefix(String protocalPrefix) {
        this.protocalPrefix = protocalPrefix;
    }
}
