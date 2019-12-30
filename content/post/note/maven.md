---
title: "maven"
date: 2019-09-09
description: "maven配置"
keywords : ["maven配置"]
tags: [maven]
categories: [笔记]
---

## settings.xml
[点击下载 一键配置脚本](/files/note/maven.zip)
* 配置中央仓库为aliyun
* 替换文件
    ```bat
    mkdir %USERPROFILE%\.m2
    copy .\settings.xml %USERPROFILE%\.m2\
    ```

## pom.xml
### 基础配置
>project
```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<build>
    <!-- 打包后的名称war/jar -->
    <finalName>pkgName</finalName>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.3.2</version>
            <configuration>
                <encoding>utf-8</encoding>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
            <!-- 找不到包的错误 -->
            <!-- <compilerArguments>
                <verbose/>
                <bootclasspath>
                    ${env.JAVA_HOME}/jre/lib/rt.jar;${env.JAVA_HOME}/jre/lib/jce.jar;${env.JAVA_HOME}/jre/lib/jsse.jar
                </bootclasspath>
            </compilerArguments> -->
            <!-- 打包跳过测试 -->
            <!-- <skip>true</skip> -->
        </plugin>
    </plugins>
</build>
```

### 测试报告
>project
```xml
<!-- 生成所有测试类报告 -->
<!-- mvn surefire-report:report -->
<!-- 生成单个测试类报告 -->
<!-- mvn surefire-report:report -Dtest=TestClassName -->
<!-- html测试报告生成目录 target/site/surefire-report.html -->
<reporting>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.surefire</groupId>
            <artifactId>surefire-report-parser</artifactId>
            <version>2.19</version>
            <!-- 打包跳过测试 -->
            <!-- <configuration>
                <skip>true</skip>
            </configuration> -->
        </plugin>
    </plugins>
</reporting>
```

### jar打包插件
>project - build - plugins
- 内部依赖方式(推荐)
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>2.3</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <mainClass>com.x.X</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```
- 外部依赖方式
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>2.6</version>
    <configuration>
        <archive>
            <manifest>
                <addClasspath>true</addClasspath>
                <classpathPrefix>lib/</classpathPrefix>
                <mainClass>com.x.X</mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>2.10</version>
    <executions>
        <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <outputDirectory>${project.build.directory}/lib</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
```
### 多环境配置
>project
```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources.env/${deploy.type}</directory>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
        </resource>
    </resources>
</build>

<!-- 打包命令：mvn clean package -Dmaven.test.skip=true -Ptest-->
<profiles>
    <!--开发环境-->
    <profile>
        <id>dev</id>
        <properties>
            <deploy.type>dev</deploy.type>
        </properties>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
    </profile>
    <!--测试环境-->
    <profile>
        <id>test</id>
        <properties>
            <deploy.type>test</deploy.type>
        </properties>
    </profile>
    <!--生产环境-->
    <profile>
        <id>prod</id>
        <properties>
            <deploy.type>prod</deploy.type>
        </properties>
    </profile>
</profiles>
```