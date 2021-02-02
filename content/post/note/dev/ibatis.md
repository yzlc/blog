---
title: "ibatis"
date: 2019-05-02T10:32:33+08:00
tags: [ibatis]
categories: [note]
---

## 批量merge
1. 参数做判断，需要传入map
    ```xml
    <statement id="merge" parameterClass="map">
    <iterate property="list" open="begin" close=";end;" conjunction=";">
        merge into...
            <isNotEmpty property="list[].id">
                id=id,
            </isNotEmpty>
    ```
2. 不用判断参数，传入list
    ```xml
    <statement id="merge" parameterClass="list">
    <iterate open="begin" close=";end;" conjunction=";">
        merge into... #list[].id#
    ```

## 集合字段映射
```xml
<resultMap class="" id="queryResult">
    <result property="ids" column="id" select="queryIds"/>
</resultMap>
```

## 常见错误
- Invalid bound statement (not found)  
只有Mapper结尾的xml文件才会被Mybatis扫描到

## 去除多余的and或者or
```xml
<trim prefix="WHERE (" suffix=")" prefixOverrides="AND |OR ">
    <if test="null != name and '' != name ">
        or name = #{name,jdbcType=VARCHAR}
    </if>
    <if test="null != mobile and '' != mobile ">
        or mobile = #{mobile,jdbcType=VARCHAR}
    </if>
</trim>
and status = 1
```