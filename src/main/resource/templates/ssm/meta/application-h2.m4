<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:jdbc="http://www.springframework.org/schema/jdbc"
  xmlns:tx="http://www.springframework.org/schema/tx"
  xmlns="http://www.springframework.org/schema/beans"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/tx
	   http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
        http://www.springframework.org/schema/jdbc
        http://www.springframework.org/schema/jdbc/spring-jdbc-4.1.xsd"
  default-lazy-init="false" default-autowire="byName">

  <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="locations">
      <list>
      </list>
    </property>
  </bean>

  <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="org.h2.Driver"/>
    <property name="url" value="jdbc:h2:mem:bone;DB_CLOSE_DELAY=-1;MODE=MySQL"/>
  </bean>

  <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"/>
    <property name="dataSource" ref="dataSource"/>
    <property name="mapperLocations" value="classpath:mybatis/mapper/*.xml"/>
  </bean>

  <bean id="readSqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <constructor-arg>
      <ref bean="sqlSessionFactory"/>
    </constructor-arg>
  </bean>
  <bean id="writeSqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <constructor-arg>
      <ref bean="sqlSessionFactory"/>
    </constructor-arg>
  </bean>

  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="${package}.mapper"/>
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
  </bean>

  <jdbc:initialize-database data-source="dataSource" ignore-failures="DROPS">
    <jdbc:script location="classpath:h2/*.sql"/>
  </jdbc:initialize-database>

  <tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true"/>
  <bean id="transactionManager"
    class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
  </bean>

</beans>