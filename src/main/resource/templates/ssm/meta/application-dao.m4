<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
            http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/tx
            http://www.springframework.org/schema/tx/spring-tx-4.1.xsd
            http://www.springframework.org/schema/aop
            http://www.springframework.org/schema/aop/spring-aop.xsd">

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>
        <property name="dataSource" ref="dataSource"/>
        <property name="typeAliasesPackage" value="${package}.entity"/>
        <property name="mapperLocations" value="classpath:mybatis/mapper/*.xml"/>
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="${package}.mapper"/>
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg>
            <ref bean="sqlSessionFactory"/>
        </constructor-arg>
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		    <property name="dataSource" ref="dataSource" />
	  </bean>

    <bean id="transactionManager"
      class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true" />

    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="add*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="insert*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="save*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="batchSave*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="delete*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="batchDelete*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="remove*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="batchRemove*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="update*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="batchUpdate*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="modify*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="batchModify*" propagation="REQUIRED" rollback-for="java.lang.Exception" />
            <tx:method name="get*" propagation="SUPPORTS" read-only="true" />
            <tx:method name="query*" propagation="SUPPORTS" read-only="true" />
            <tx:method name="find*" propagation="SUPPORTS" read-only="true" />
            <tx:method name="load*" propagation="SUPPORTS" read-only="true" />
            <tx:method name="select*" propagation="SUPPORTS" read-only="true" />
        </tx:attributes>
    </tx:advice>

    <aop:config>
        <aop:pointcut id="txMethods"
          expression="execution(* ${package}.service.impl..*.*(..))" />
        <aop:advisor advice-ref="txAdvice"
          pointcut-ref="txMethods" />
    </aop:config>

</beans>