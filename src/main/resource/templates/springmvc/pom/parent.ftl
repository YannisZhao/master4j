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
    </properties>

</project>
