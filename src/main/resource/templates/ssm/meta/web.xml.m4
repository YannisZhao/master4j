<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">
  <display-name>Archetype Created Web Application</display-name>
  <welcome-file-list>
    <welcome-file>login.html</welcome-file>
  </welcome-file-list>
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  <!--<filter>
    <filter-name>userLoginFilter</filter-name>
    <filter-class>org.yannis.swall.web.filter.UserLoginFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>userLoginFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>-->
  <!--<filter>
    <filter-name>paramFilter</filter-name>
    <filter-class>org.yannis.swall.web.filter.ParamFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>paramFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>-->
  <servlet>
    <servlet-name>SpringMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath*:application-*.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>SpringMVC</servlet-name>
    <url-pattern>*.action</url-pattern>
  </servlet-mapping>
</web-app>