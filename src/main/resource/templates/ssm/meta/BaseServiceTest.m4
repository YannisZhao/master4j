/**
* Generated by master4j at ${date}
*
* Copyright (c) 2015-2025 Yannis Zhao, All rights reserved.
*
* http://www.yanniszhao.org     zhaoyjun0222@gmail.com
*
*/
package ${package};

import java.io.FileNotFoundException;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.Log4jConfigurer;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class BaseTest {

    @Autowired
    private DataSource dataSource;

    protected JdbcTemplate jdbcTemplate;

    protected Jedis jedis;

    @BeforeClass
    public static void setupBeforeClass() throws FileNotFoundException {
        Log4jConfigurer.initLogging("classpath:log/log4j.properties");
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        jedis = new Jedis("127.0.0.1", 6379);
    }

    protected void clearTable(String tableName) {
        jdbcTemplate.update("DELETE FROM " + tableName);
    }

    protected void clearCache(String key) {
        jedis.del(key);
    }

    protected void clearCaches(String pattern) {
        Set<String> keys = jedis.keys(pattern);
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        String[] keyArray = new String[keys.size()];
        jedis.del(keys.toArray(keyArray));
    }
}
