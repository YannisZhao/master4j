<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
  xmlns:context="http://www.springframework.org/schema/context"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
		http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop-4.1.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-4.1.xsd">

	<context:component-scan base-package="${package}" />
	<context:annotation-config/>
	<aop:aspectj-autoproxy/>

	<import resource="application-services.xml"/>
  <import resource="application-dao.xml"/>
  <import resource="application-redis.xml"/>

</beans>