<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
            http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/tx
			http://www.springframework.org/schema/tx/spring-tx-3.0.xsd">

    <!--<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="locations">
            <list>
                <!--<value>/WEB-INF/db.properties</value>-->
                <value>classpath*: db.properties</value>
            </list>
        </property>
    </bean>-->

    <!-- 数据库连接池 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/uaas?useUnicode=true&amp;characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull"/>
        <property name="username" value="root"/>
        <property name="password" value="111111"/>
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource" />
	</bean>

    <!-- 配置事务 -->
    <bean id="defaultTransactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>

    <!-- 使用annotation定义事务 -->
    <tx:annotation-driven transaction-manager="defaultTransactionManager" proxy-target-class="true" />

    <!-- Base DAO -->
    <!--<bean id="baseDao" abstract="true">
        <property name="dataSource" ref="dataSource"/>
    </bean>-->

</beans>