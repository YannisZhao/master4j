<#include "../../lib/declare.ftl"/>
<#include "../../lib/data.ftl"/>
<#macro type_wrapper type>
<#if type=="Integer">
Int
<#elseif type=="long">
Long
<#else>
${type}
</#if>
</#macro>
<#assign columns>
<#list fields as field>${field.name}<#sep>, </#list>
</#assign>
<#assign idPrepared>
<#list fields as field><#if field.primary>${field.name}=?<#if field_has_next>, <#else></#if></#if></#list>
</#assign>
<#assign select>
SELECT ${columns} FROM ${tableMeta.tableName}
</#assign>
<#assign idPrepared>${idPrepared?substring(0, idPrepared?length-2)}</#assign>
<#assign insert>
<@compress_single_line>
"INSERT INTO ${tableMeta.tableName}(${columns}) values(
<#list fields as field>?<#sep>, </#list>
)";
</@compress_single_line>
</#assign>
<#assign update>
<@compress_single_line>
<#assign space=" ">
"UPDATE ${tableMeta.tableName} SET${space}
<#list fields as field>
<#if !field.primary>
${field.name}=?<#if field_has_next>, <#else> </#if>
</#if>
</#list>
WHERE
 ${idPrepared}
";
</@compress_single_line>
</#assign>
package ${package};

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import org.yannis.commons.web.page.Paginator;
import org.yannis.commons.web.request.data.QueryBuilder;
import org.yannis.commons.web.request.http.GeneralQueryRequest;

${imports}
import ${basePackageName}.dao.BaseDao;
import ${basePackageName}.dao.${baseClassName};
import ${basePackageName}.domain.${domainName};

/**
${classDoc}
*/
@Repository
public class ${className} extends BaseDao implements ${baseClassName} {

    private final BeanPropertyRowMapper<${domainName}> rowMapper = BeanPropertyRowMapper.newInstance(${domainName}.class);

    @Override
    public ${domainName} <@compress_single_line>findById(${idParams}) {</@compress_single_line>
        ${domainName} entity = <@compress_single_line>jdbcTemplate.queryForObject("${select} WHERE ${idPrepared}", new Object[]{${idValues}}, rowMapper);</@compress_single_line>
        return entity;
    }

    @Override
    public List<${domainName}> findByPage(Paginator paginator) {
        StringBuilder query = <@compress_single_line>new StringBuilder("${select}");</@compress_single_line>
        List<Object> values = new ArrayList<>();
        Map<String, String> sorts = paginator.getSorts();

        buildOrderQuery(query, values, sorts);
        query.append(" LIMIT ?,?");
        values.add(paginator.getRowStart());
        values.add(paginator.getPageSize());

        List<${domainName}> entities = jdbcTemplate.query(query.toString(), values.toArray(), rowMapper);

        return entities;
    }

    @Override
    public List<${domainName}> search(GeneralQueryRequest request) {
        StringBuilder query = <@compress_single_line>new StringBuilder("${select} WHERE 1=1");</@compress_single_line>
        QueryBuilder queryBuilder = request.getQueryBuilder();
        if(queryBuilder !=null){
            query.append(" AND " + queryBuilder.getQuery());
        }
        List<Object> values = new ArrayList<>();
        Map<String, String> sorts = request.getSorts();

        buildOrderQuery(query, values, sorts);
        query.append(" LIMIT ?,?");
        values.add(request.getRowStart());
        values.add(request.getPageSize());

        List<${domainName}> entities = jdbcTemplate.query(
            query.toString(),
            ArrayUtils.addAll(queryBuilder.getValues(), values.toArray()), rowMapper);

        return entities;
    }

    @Override
    public long getTotalRows() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM ${tableMeta.tableName}", Long.class);
    }

    @Override
    public long getTotalRows(GeneralQueryRequest request) {
        StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM ${tableMeta.tableName}");
        QueryBuilder queryBuilder = request.getQueryBuilder();
        if(queryBuilder != null){
            query.append(" WHERE" + queryBuilder.getQuery());
        }
        return jdbcTemplate.queryForObject(query.toString(), queryBuilder.getValues(), Long.class);
    }

    @Override
    public boolean save(${domainName} obj) {
        if(obj == null){
            throw new NullPointerException("Null object found when save data.");
        }

        StringBuffer insertion = new StringBuffer("INSERT INTO ${tableMeta.tableName}(");
        StringBuffer placeHolder = new StringBuffer("value(");
        List<Object> values = new ArrayList<>();

        <#list fields as field>
        if(obj.get${field.name?cap_first}() != ${field.comparedDefaultValue!"null"}){
            insertion.append("${field.name},");
            placeHolder.append("?,");
            values.add(obj.get${field.name?cap_first}());
        }
        </#list>

        placeHolder.deleteCharAt(placeHolder.length()-1).append(")");
        insertion.deleteCharAt(insertion.length()-1).append(") ").append(placeHolder);

        String query = insertion.toString();

        int affectedRows = 0;
        try {
            affectedRows = jdbcTemplate.update(query, values);
        } catch (DuplicateKeyException e) {
            //throw new DaoException(ExceptionMeta.E40001);
        }

        if (1 == affectedRows) {
            return true;
        }

        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, timeout = 30, rollbackFor = {
            RuntimeException.class, Exception.class })
    @Override
    public int batchSave(List<${domainName}> objs) {
    final List<${domainName}> ${domainName?uncap_first}s = objs;
        String query = ${insert}
        int[] result = null;
        try {
            result = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    <#list fields as field>
                    <@compress_single_line>ps.set<@type_wrapper type="${field.type}" />(${field_index+1}, ${domainName?uncap_first}s.get(i).get${field.name?cap_first}());</@compress_single_line>
                    </#list>
                }
                public int getBatchSize() {
                    return ${domainName?uncap_first}s.size();
                }
            });
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Duplicate Key");
        }

        int i = 0;
        for (int v : result) {
            i += v;
        }

        return i;
    }

    @Override
    public boolean <@compress_single_line>remove(${idParams}) {</@compress_single_line>
        int affectedRows = <@compress_single_line>jdbcTemplate.update("DELETE FROM ${tableMeta.tableName} WHERE ${idPrepared}", new Object[]{${idValues}});</@compress_single_line>
        if(1 == affectedRows){
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, timeout = 30, rollbackFor = {
            RuntimeException.class, Exception.class })
    @Override
    <@compress_single_line>
    public int batchRemove(${batchRemoveParams}) {
    </@compress_single_line>

        <#if pks?size == 1>
        if(ids == null || ids.length == 0){
            throw new RuntimeException("Null value");
        }
        <#else>
        if(pks == null || pks.size() == 0){
            throw new RuntimeException("Null value");
        }
        </#if>

        StringBuilder query = new StringBuilder("DELETE FROM ${tableMeta.tableName} WHERE 1=-1");

        List<Object> values = new ArrayList<>();

        <#if pks?size == 1>
        for(String id : ids){
            query.append(" OR (id=?)");
            values.add(id);
        }
        <#else>
        for(Map<String, String> pk : pks){
            query.append(" OR ("+pk.get("id")+"=?)");
            values.add(pk.get("value"));
        }
        </#if>

        int affectedRows = jdbcTemplate.update(query.toString(), values.toArray());

        return affectedRows;
    }

    @Override
    public boolean update(${domainName} obj) {

        assert obj != null;

        String str = "UPDATE ${tableMeta.tableName} SET";

        StringBuilder query = new StringBuilder(str);

        List<Object> values = new ArrayList<>();

        <#list fields as field>
        <#if !field.primary>
        if(obj.get${field.name?cap_first}() != ${field.comparedDefaultValue!"null"}){
            query.append(" ${field.name}=? AND");
            values.add(obj.get${field.name?cap_first}());
        }
        </#if>
        </#list>

        query.delete(query.length()-4, query.length()).append(" WHERE");

        <#list fields as field>
        <#if field.primary>
        query.append(" ${field.name}=?<#--<#sep> AND</#sep>-->");
        values.add(obj.get${field.name?cap_first}());
        </#if>
        </#list>

        try {
            jdbcTemplate.update(query.toString(), values.toArray());
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Duplication key");
        }

        return true;
    }

    @Override
    public int batchUpdate(List<${domainName}> objs) {
        final List<${domainName}> ${domainName?uncap_first}s = objs;
        String query = ${update}
        int[] result = null;
        try {
            result = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                <#assign i = 1 />
                <#list fields as field>
                    <#if !field.primary>
                    <@compress_single_line>ps.set<@type_wrapper type="${field.type}" />(${i}, ${domainName?uncap_first}s.get(i).get${field.name?cap_first}());</@compress_single_line>
                    <#assign i=i+1 />
                    </#if>
                </#list>
                <#list fields as field>
                    <#if field.primary>
                    <@compress_single_line>ps.set<@type_wrapper type="${field.type}" />(${i}, ${domainName?uncap_first}s.get(i).get${field.name?cap_first}());</@compress_single_line>
                    <#assign i=i+1 />
                    </#if>
                </#list>
                }
                public int getBatchSize() {
                    return ${domainName?uncap_first}s.size();
                }
            });
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Duplicate Key");
        }
        int i = 0;
        for (int v : result) {
            i += v;
        }
        return i;
    }

}