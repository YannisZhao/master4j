<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>

    <groupId>${group}</groupId>
    <artifactId>${module}-parent</artifactId>
    <name>${module}-parent</name>
    <version>${version}</version>

    <url>http://maven.apache.org</url>

    <modules>
        <module>${module}-api</module>
        <module>${module}</module>
        <module>${module}-web</module>
    </modules>

    <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <java.version>1.8</java.version>
      <org.yannis.commons.version>1.0-SNAPSHOT</org.yannis.commons.version>
      <mysql-connector-java.version>5.1.42</mysql-connector-java.version>
      <druid.version>1.1.2</druid.version>
      <mybatis.version>3.3.1</mybatis.version>
      <mybatis-spring.version>1.2.5</mybatis-spring.version>
      <jedis.version>2.9.0</jedis.version>
      <org.springframework.version>4.2.4.RELEASE</org.springframework.version>
      <validation-api.version>1.1.0.Final</validation-api.version>
      <el-api.version>3.0.0</el-api.version>
      <hibernate-validator.version>5.3.0.Final</hibernate-validator.version>
      <commons-fileupload.version>1.3.2</commons-fileupload.version>
      <jackson-databind.version>2.5.0</jackson-databind.version>
      <jackson-core-asl.version>1.9.12</jackson-core-asl.version>
      <commons-lang3.version>3.5</commons-lang3.version>
      <commons-io.version>2.5</commons-io.version>
      <guava.version>20.0</guava.version>
      <fastjson.version>1.2.31</fastjson.version>
      <junit.version>4.12</junit.version>
      <mockito.version>2.7.22</mockito.version>
      <h2.version>1.4.196</h2.version>
      <slf4j.version>1.7.5</slf4j.version>
      <slf4j-log4j12.version>1.7.21</slf4j-log4j12.version>
    </properties>

    <dependencyManagement>
      <dependencies>
        <!-- user lib -->
        <dependency>
          <groupId>org.yannis.commons</groupId>
          <artifactId>common-web</artifactId>
          <version>${r'${org.yannis.commons.version}'}</version>
        </dependency>

        <!-- db lib -->
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>${r'${mysql-connector-java.version}'}</version>
        </dependency>
        <dependency>
          <groupId>com.alibaba</groupId>
          <artifactId>druid</artifactId>
          <version>${r'${druid.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>${r'${mybatis.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis-spring</artifactId>
          <version>${r'${mybatis-spring.version}'}</version>
        </dependency>

        <!-- cache lib -->
        <dependency>
          <groupId>redis.clients</groupId>
          <artifactId>jedis</artifactId>
          <version>${r'${jedis.version}'}</version>
        </dependency>

        <!-- utils lib -->
        <dependency>
          <groupId>org.apache.commons</groupId>
          <artifactId>commons-lang3</artifactId>
          <version>${r'${commons-lang3.version}'}</version>
        </dependency>
        <dependency>
          <groupId>commons-io</groupId>
          <artifactId>commons-io</artifactId>
          <version>${r'${commons-io.version}'}</version>
        </dependency>
        <dependency>
          <groupId>commons-fileupload</groupId>
          <artifactId>commons-fileupload</artifactId>
          <version>${r'${commons-fileupload.version}'}</version>
        </dependency>
        <dependency>
          <groupId>com.google.guava</groupId>
          <artifactId>guava</artifactId>
          <version>${r'${guava.version}'}</version>
        </dependency>
        <dependency>
          <groupId>com.alibaba</groupId>
          <artifactId>fastjson</artifactId>
          <version>${r'${fastjson.version}'}</version>
        </dependency>
        <dependency>
          <groupId>javax.validation</groupId>
          <artifactId>validation-api</artifactId>
          <version>${r'${validation-api.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.hibernate</groupId>
          <artifactId>hibernate-validator</artifactId>
          <version>${r'${hibernate-validator.version}'}</version>
        </dependency>
        <dependency>
          <groupId>javax.el</groupId>
          <artifactId>javax.el-api</artifactId>
          <version>${r'${el-api.version}'}</version>
        </dependency>
        <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-databind</artifactId>
          <version>${r'${jackson-databind.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.codehaus.jackson</groupId>
          <artifactId>jackson-core-asl</artifactId>
          <version>${r'${jackson-core-asl.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.codehaus.jackson</groupId>
          <artifactId>jackson-mapper-asl</artifactId>
          <version>${r'${jackson-core-asl.version}'}</version>
        </dependency>

        <!-- spring lib -->
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${r'${org.springframework.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-beans</artifactId>
          <version>${r'${org.springframework.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-aop</artifactId>
          <version>${r'${org.springframework.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-tx</artifactId>
          <version>${r'${org.springframework.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-jdbc</artifactId>
          <version>${r'${org.springframework.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-webmvc</artifactId>
          <version>${r'${org.springframework.version}'}</version>
        </dependency>

        <!-- test lib -->
        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>${r'${junit.version}'}</version>
          <scope>test</scope>
        </dependency>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-test</artifactId>
          <version>${r'${org.springframework.version}'}</version>
          <scope>test</scope>
        </dependency>
        <dependency>
          <groupId>org.mockito</groupId>
          <artifactId>mockito-core</artifactId>
          <version>${r'${mockito.version}'}</version>
          <scope>test</scope>
        </dependency>
        <dependency>
          <groupId>com.h2database</groupId>
          <artifactId>h2</artifactId>
          <version>${r'${h2.version}'}</version>
          <scope>test</scope>
        </dependency>

        <!-- log lib -->
        <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-api</artifactId>
          <version>${r'${slf4j.version}'}</version>
        </dependency>
        <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-log4j12</artifactId>
          <version>${r'${slf4j-log4j12.version}'}</version>
        </dependency>

      </dependencies>
    </dependencyManagement>

    <build>
      <pluginManagement>
        <plugins>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.2</version>
            <configuration>
              <encoding>${r'${project.build.sourceEncoding}'}</encoding>
              <source>${r'${java.version}'}</source>
              <target>${r'${java.version}'}</target>
              <compilerVersion>${r'${java.version}'}</compilerVersion>
            </configuration>
          </plugin>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-source-plugin</artifactId>
            <version>2.4</version>
            <executions>
              <execution>
                <phase>package</phase>
                <goals>
                  <goal>jar</goal>
                  <goal>test-jar</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-javadoc-plugin</artifactId>
            <version>2.10.1</version>
            <executions>
              <execution>
                <phase>package</phase>
                <goals>
                  <goal>jar</goal>
                  <goal>javadoc</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.19.1</version>
            <configuration>
              <skip>false</skip>
              <includes>
                <include>**/*Test.java</include>
              </includes>
              <forkMode>always</forkMode>
              <argLine>-Dlog4j.configuration=</argLine>
            </configuration>
            <executions>
              <execution>
                <id>test</id>
                <phase>verify</phase>
                <goals>
                  <goal>test</goal>
                </goals>
                <inherited>false</inherited>
                <configuration>
                  <parallel>false</parallel>
                </configuration>
              </execution>
            </executions>
          </plugin>
        </plugins>
      </pluginManagement>

      <plugins>
        <plugin>
          <artifactId>maven-javadoc-plugin</artifactId>
        </plugin>
        <plugin>
          <artifactId>maven-source-plugin</artifactId>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
        </plugin>
      </plugins>
    </build>

</project>
