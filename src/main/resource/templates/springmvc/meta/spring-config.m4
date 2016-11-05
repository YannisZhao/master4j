<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="  
            http://www.springframework.org/schema/beans       
            http://www.springframework.org/schema/beans/spring-beans-3.0.xsd  
            http://www.springframework.org/schema/context   
            http://www.springframework.org/schema/context/spring-context-3.0.xsd
            http://www.springframework.org/schema/mvc
			http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">

	<context:component-scan base-package="${package}" />
	<!--<context:component-scan base-package="org.yannis.xscheduler.web" />-->
	<!-- 注册DefaultAnnotationHandlerMapping和AnnotationMethodHandlerAdapter两个bean -->
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
	
	<!-- <bean
		class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
		<property name="ignoreAcceptHeader" value="true" />
		<property name="defaultContentType" value="text/html" />
		<property name="mediaTypes">
			<map>
				<entry key="json" value="application/json" />
			</map>
		</property>
		<property name="favorParameter" value="false" />
		<property name="viewResolvers">
			<list>
				<bean class="org.springframework.web.servlet.view.BeanNameViewResolver" />
				<bean
					class="org.springframework.web.servlet.view.InternalResourceViewResolver">
					<property name="viewClass"
						value="org.springframework.web.servlet.view.JstlView" />
					<property name="prefix" value="/" />
					<property name="suffix" value=".jsp"></property>
				</bean>
			</list>
		</property>
		<property name="defaultViews">
			<list>
				<bean
					class="org.springframework.web.servlet.view.json.MappingJacksonJsonView"></bean>
			</list>
		</property>
	</bean> -->

	<!-- Global exception resolver -->
	<!-- <bean id="exceptionResolver"
		class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
		<property name="defaultErrorView">
			<value>/page/error</value>
		</property>
		<property name="defaultStatusCode">
			<value>500</value>
		</property>
		<property name="warnLogCategory">
			<value>org.springframework.web.servlet.handler.SimpleMappingExceptionResolver</value>
		</property>
	</bean> -->

</beans>