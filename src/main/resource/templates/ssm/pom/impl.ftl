<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

    <parent>
      <artifactId>${module}-parent</artifactId>
      <groupId>${group}</groupId>
      <version>${version}</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>jar</packaging>

    <groupId>${group}</groupId>
    <artifactId>${module}</artifactId>
    <name>${module}</name>


    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <useDefaultDelimiters>false</useDefaultDelimiters>
    </properties>

    <dependencies>

      <dependency>
        <groupId>${group}</groupId>
        <artifactId>${module}-api</artifactId>
        <version>${r'${project.version}'}</version>
      </dependency>

      <!-- test lib -->
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <scope>test</scope>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <scope>test</scope>
      </dependency>
      <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <scope>test</scope>
      </dependency>
      <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>1.4.196</version>
        <scope>test</scope>
      </dependency>

      <!-- database -->
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
      </dependency>
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
      </dependency>
      <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
      </dependency>

      <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
      </dependency>

      <!-- aop lib -->
      <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjrt</artifactId>
      </dependency>
      <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
      </dependency>
      <dependency>
        <groupId>aopalliance</groupId>
        <artifactId>aopalliance</artifactId>
      </dependency>

      <!-- spring -->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
      </dependency>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
      </dependency>

      <!-- utils lib -->
      <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
      </dependency>
      <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
      </dependency>
      <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
      </dependency>
      <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
      </dependency>

      <!-- log -->
      <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
      </dependency>
      <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
      </dependency>

    </dependencies>

    <build>

        <finalName>${module}</finalName>
        <sourceDirectory>src/main/java</sourceDirectory>
        <testSourceDirectory>src/test/java</testSourceDirectory>
        <outputDirectory>target/classes</outputDirectory>
        <testOutputDirectory>target/test-classes</testOutputDirectory>

        <resources>
          <resource>
            <directory>src/main/resources</directory>
          </resource>
        </resources>

        <testResources>
          <testResource>
            <directory>src/test/resources</directory>
          </testResource>
        </testResources>

        <directory>target</directory>

        <plugins>

          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
              <includes>
                <include>**/Test.java</include>
              </includes>
            </configuration>
          </plugin>

          <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>cobertura-maven-plugin</artifactId>
            <version>2.6</version>
            <configuration>
              <formats>
                <format>xml</format>
              </formats>
              <check/>
            </configuration>
          </plugin>

        </plugins>
    </build>

</project>
