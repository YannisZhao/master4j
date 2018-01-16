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

      <dependency>
        <groupId>org.yannis.commons</groupId>
        <artifactId>common-web</artifactId>
      </dependency>

      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
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
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <scope>test</scope>
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
