<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

    <parent>
      <artifactId>${module}-parent</artifactId>
      <groupId>${group}</groupId>
      <version>${version}</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>war</packaging>

    <groupId>${group}</groupId>
    <artifactId>${module}-web</artifactId>
    <name>${module}-web</name>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <!-- user lib -->
        <dependency>
            <groupId>${group}</groupId>
            <artifactId>${module}-api</artifactId>
            <version>${version}</version>
        </dependency>
        <dependency>
            <groupId>${group}</groupId>
            <artifactId>${module}</artifactId>
            <version>${version}</version>
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

        <!-- spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <scope>test</scope>
        </dependency>
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
          <artifactId>spring-webmvc</artifactId>
        </dependency>

        <dependency>
          <groupId>javax.validation</groupId>
          <artifactId>validation-api</artifactId>
        </dependency>
        <dependency>
          <groupId>javax.el</groupId>
          <artifactId>javax.el-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
        </dependency>

        <dependency>
          <groupId>commons-fileupload</groupId>
          <artifactId>commons-fileupload</artifactId>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-core-asl</artifactId>
        </dependency>
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
        </dependency>
        <dependency>
          <groupId>com.alibaba</groupId>
          <artifactId>fastjson</artifactId>
        </dependency>
    </dependencies>

    <build>
        <finalName>${module}-war</finalName>
        <sourceDirectory>src/main/java</sourceDirectory>
        <testSourceDirectory>src/test/java</testSourceDirectory>
        <outputDirectory>target/classes</outputDirectory>
        <testOutputDirectory>target/test-classes</testOutputDirectory>

        <resources>
          <resource>
            <directory>src/main/resources</directory>
          </resource>
          <resource>
            <directory>src/main/webapp</directory>
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
                <artifactId>maven-source-plugin</artifactId>
                <version>2.4</version>
                <executions>
                    <execution>
                        <id>attach-sources</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>jar</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
