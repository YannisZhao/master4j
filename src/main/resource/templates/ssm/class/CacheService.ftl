<#include "../../lib/declare.ftl"/>
<#include "../../lib/data.ftl"/>
package ${package};

${imports}
import java.util.List;
<#if pks?size gt 1>
import java.util.Map;
</#if>

import static com.google.common.base.Preconditions.checkNotNull;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import ${basePackageName}.api.dto.${dtoName};

/**
* ${dtoName} cache service
*/
@Service
public class ${className} {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);
    
    // cache key patterns
    private static final String ${dtoName?upper_case}_DATA_CACHE_KEY = "${dtoName?upper_case}:DATA:%s";
    
    // cache time constants
    private static final int CACHE_TIME_DAYS_30 = 30 * 24 * 60 * 60;
    
    @Autowired
    private ShardedJedisPool jedisPool;
    
    public void save${dtoName}(${dtoName} ${dtoName?uncap_first}) {
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(get${dtoName}Key(${dtoName?uncap_first}.getId()), CACHE_TIME_DAYS_30, serialize(${dtoName?uncap_first}));
        } finally {
            close(jedis);
        }
    }
    
    public void save${dtoName}s(List<${dtoName}> ${dtoName?uncap_first}s) {
        ShardedJedis jedis = null;
        try {
          jedis = jedisPool.getResource();
          ShardedJedisPipeline pipelined = jedis.pipelined();
          for (${dtoName} ${dtoName?uncap_first} : ${dtoName?uncap_first}s) {
            pipelined.setex(get${dtoName}Key(${dtoName?uncap_first}.getId()), CACHE_TIME_DAYS_30, serialize(${dtoName?uncap_first}));
          }
        
          pipelined.sync();
        } finally {
          close(jedis);
        }
    }
        
    public void remove${dtoName}(Long ${dtoName?uncap_first}Id) {
        ShardedJedis jedis = null;
        try {
          jedis = jedisPool.getResource();
          jedis.del(get${dtoName}Key(${dtoName?uncap_first}Id));
        } finally {
            close(jedis);
        }
    }
    
    public void remove${dtoName}s(List<Long> ${dtoName?uncap_first}Ids) {
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            ShardedJedisPipeline pipelined = jedis.pipelined();
        
            for (Long ${dtoName?uncap_first}Id : ${dtoName?uncap_first}Ids) {
                pipelined.del(get${dtoName}Key(${dtoName?uncap_first}Id));
            }
        
            pipelined.sync();
        } finally {
            close(jedis);
        }
    
    }
    
    public ${dtoName} get${dtoName}(Long ${dtoName?uncap_first}Id) {
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String json = jedis.get(get${dtoName}Key(${dtoName?uncap_first}Id));
            return deserialize(json);
        } finally {
            close(jedis);
        }
    
    }
    
    public Map<Long, ${dtoName}> batchGet${dtoName}s(List<Long> ${dtoName?uncap_first}Ids) {
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            ShardedJedisPipeline pipelined = jedis.pipelined();
    
            int size = ${dtoName?uncap_first}Ids.size();
            String[] keys = new String[size];
            for (int i = 0; i < size; i++) {
                keys[i] = get${dtoName}Key(${dtoName?uncap_first}Ids.get(i));
            }

            List<Response<String>> responses = new ArrayList<>(size);
            for (String key : keys) {
            responses.add(pipelined.get(key));
            }
    
            pipelined.sync();
    
            Map<Long, ${dtoName}> cacheMap = new HashMap<>(size);
            for (int i = 0; i < responses.size(); i++) {
                cacheMap.put(${dtoName?uncap_first}Ids.get(i), deserialize(responses.get(i).get()));
            }
    
            return cacheMap;
        } finally {
            close(jedis);
        }

    }
        
    private String serialize(${dtoName} ${dtoName?uncap_first}) {
        return JSON.toJSONString(${dtoName?uncap_first}.toString());
    }

    private ${dtoName} deserialize(String json) {
        return JSON.parseObject(json, ${dtoName}.class);
    }

    private String get${dtoName}Key(Long ${dtoName?uncap_first}Id) {
        return String.format(${dtoName?upper_case}_DATA_CACHE_KEY, checkNotNull(${dtoName?uncap_first}Id));
    }

    private void close(ShardedJedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                LOGGER.error("close jedis error", e);
            }
        }
    }
}