<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-4.1.xsd">

  <!-- TODO: config properties -->
  <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
    <property name="maxTotal" value="${redis.poolConfig.maxTotal}"/>
    <property name="minIdle" value="${redis.poolConfig.minIdle}"/>
    <property name="maxIdle" value="${redis.poolConfig.maxIdle}"/>
    <property name="maxWaitMillis" value="${redis.poolConfig.maxWaitMillis}"/>
    <property name="testOnBorrow" value="${redis.poolConfig.testOnBorrow}"/>
  </bean>

  <bean id="jedisShardInfo" class="redis.clients.jedis.JedisShardInfo">
    <constructor-arg index="0" value="${redis.shardInfo.address}"/>
    <constructor-arg index="1" value="${redis.shardInfo.port}" type="int"/>
    <property name="password" value="${redis.shardInfo.password}"/>
  </bean>


  <bean id="shardedJedisPool" class="redis.clients.jedis.ShardedJedisPool">
    <constructor-arg index="0" ref="jedisPoolConfig"/>
    <constructor-arg index="1">
      <list>
        <ref bean="jedisShardInfo"/>
      </list>
    </constructor-arg>
  </bean>

</beans>
