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
    <artifactId>${module}-api</artifactId>
    <name>${module}-api</name>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
          <groupId>org.yannis.commons</groupId>
          <artifactId>common-web</artifactId>
        </dependency>
    </dependencies>

    <build>
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
