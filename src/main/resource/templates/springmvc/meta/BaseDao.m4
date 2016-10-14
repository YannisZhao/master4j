package ${package};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public abstract class BaseDao {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	protected void buildOrderQuery(StringBuilder query, List<Object> values, Map<String, String> sorts) {
		if(sorts!=null && !sorts.isEmpty()) {
			query.append(" ORDER BY");
			for (String key : sorts.keySet()) {
				query.append(" ? ?,");
				values.add(key);
				values.add(sorts.get(key));
			}
			query.deleteCharAt(query.length()-1);
		}
	}

}
