package ${package};

${imports}

import org.yannis.test.dao.${baseClassName};

/**
${classDoc}
*/
public class ${className} implements ${baseClassName} {

    private final BeanPropertyRowMapper<${domainName}> rowMapper = BeanPropertyRowMapper.newInstance(${domainName}.class);

    @Override
    public abstract ${dtoName} findById(String id) throws DaoException {
        ${domainName} entity = jdbcTemplate.queryForObject("SELECT * FROM application WHERE id = ?", new String[]{id}, rowMapper);
        return ${convertName}.entity2DTO(entity);
    }

    @Override
    public abstract List<${domainName}> findByPage(SqlParamWrapper[] params, Paginator paginator) throws DaoException {
        return jdbcTemplate.query(
                "SELECT * FROM application LIMIT ?,?",
                new Object[]{paginator.getRowStart(), paginator.getPageSize()}, rowMapper);
    }

    @Override
    public abstract long getTotalRows(SqlParamWrapper[] params) throws DaoException {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM application", new Object[]{}, Long.class);
    }

    @Override
    public abstract boolean save(${domainName} obj) throws DaoException {
        if(obj == null){
            throw new NullPointerException("Null object found when save data.");
        }

        ${domainName} entity = ${convertName}.dto2Entity(obj);

        StringBuffer insertion = new StringBuffer("INSERT INTO application(");
        StringBuffer placeHolder = new StringBuffer("value(");
        List<Object> values = new ArrayList<>();

        <#list fields as field>
        if(entity.get${field.name?cap_first}()!=0){
            insertion.append("${field.name},");
            placeHolder.append("?,");
            values.add(entity.get${field.name?cap_first}());
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

    @Override
    public abstract int batchSave(List<${domainName}> objs) throws DaoException {
        return 0;
    }

    @Override
    public abstract boolean remove(String id) throws DaoException {
        int affectedRows = jdbcTemplate.update("DELETE FROM application WHERE id = ?", new Object[]{id});
        if(1 == affectedRows){
            return true;
        }
        return false;
    }

    @Override
    public abstract int batchRemove(String[] ids) throws DaoException {
        return 0;
    }

    @Override
    public abstract boolean update(${domainName} obj) throws DaoException {
        return false;
    }

    @Override
    public abstract int batchUpdate(List<${domainName}> objs) throws DaoException {
        final List<${domainName}> applications = objs;
        String sql = "";
        int[] result = null;
        try {
            result = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, applications.get(i).getId());
                    ps.setString(2, applications.get(i).getAppKey());
                }
                public int getBatchSize() {
                    return applications.size();
                }
            });
        } catch (DuplicateKeyException e) {
            //throw new DaoException(ExceptionMeta.E40006);
        }
        int i = 0;
        for (int v : result) {
            i += v;
        }

        return i;
    }

}