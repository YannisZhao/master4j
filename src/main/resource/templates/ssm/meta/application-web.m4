<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="  
            http://www.springframework.org/schema/beans       
            http://www.springframework.org/schema/beans/spring-beans-4.1.xsd
            http://www.springframework.org/schema/context   
            http://www.springframework.org/schema/context/spring-context-4.1.xsd
            http://www.springframework.org/schema/mvc
			http://www.springframework.org/schema/mvc/spring-mvc-4.1.xsd">

	<context:component-scan base-package="${package}" />
	<!--<context:component-scan base-package="org.yannis.xscheduler.web" />-->
	<mvc:annotation-driven validator="validator" />

	<!-- <mvc:default-servlet-handler/>   -->

	<bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
    		<property name="providerClass" value="org.hibernate.validator.HibernateValidator"/>
    		<property name="validationMessageSource" ref="messageSource"/>
    </bean>
	
	<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
		<property name="basenames">
			<list>
				<value>i18n/message/messages</value>
        <value>i18n/label/labels</value>
			</list>
		</property>
	</bean>
	<bean id="localeResolver" class="org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver">
	</bean>
	
	<!-- <bean id="servletHandlerAdapter"
		class="org.springframework.web.servlet.handler.SimpleServletHandlerAdapter" /> -->
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize">
			<value>1048576</value>
		</property>
	</bean>

	<!-- View resolver -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		
		<property name="prefix">
			<value>/WEB-INF/view/</value>
		</property>
		<property name="suffix">
			<value>.jsp</value>
		</property>
	</bean>
	
	<bean id="mappingJackson2HttpMessageConverter"  
        class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">  
        <property name="supportedMediaTypes">  
            <list>  
                <value>text/html;charset=UTF-8</value>  
                <value>text/json;charset=UTF-8</value>  
                <value>application/json;charset=UTF-8</value>  
            </list>  
        </property>  
    </bean>

	<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
    <property name="messageConverters">  
        <list>  
            <ref bean="mappingJackson2HttpMessageConverter" />
        </list>  
    </property>  
  </bean>
	
</beans>