package org.yannis.master4j.meta;

import java.util.List;

/**
 * Created by yannis on 6/13/16.
 */
public class DatabaseMeta {

    private List<TableMeta> tableMetaList;

    public List<TableMeta> getTableMetaList() {
        return tableMetaList;
    }

    public void setTableMetaList(List<TableMeta> tableMetaList) {
        this.tableMetaList = tableMetaList;
    }
}
