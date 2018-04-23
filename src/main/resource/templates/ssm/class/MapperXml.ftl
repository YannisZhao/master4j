<#include "../../lib/data.ftl"/>
<#assign columns>
  <#list fields as field>${field.column}<#sep>, </#list>
</#assign>
<?xml version="1.0" encoding="UTF-8" ?>
${r'<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >'}
<mapper namespace="${package}.${className}">

  <resultMap id="BaseResultMap" type="${entityName}">
  <#list fields as field>
    <#if field.primary>
    <id column="${field.column}" property="${field.name}" jdbcType="${field.jdbcType}"/>
    <#else>
    <result column="${field.column}" property="${field.name}" jdbcType="${field.jdbcType}"/>
    </#if>
  </#list>
  </resultMap>

  <sql id="tbl_name">${tableMeta.tableName}</sql>

  <sql id="full_columns">
  <@compress_single_line>`${columns}`</@compress_single_line>
  </sql>

  <insert id="insert" parameterType="${entityName}">
  <#list fields as field>
    <#if field.primary>
      <selectKey resultType="java.lang.${field.type}" keyProperty="${field.name}" order="AFTER">
        SELECT LAST_INSERT_ID()
      </selectKey>
    </#if>
  </#list>
    INSERT INTO
    <include refid="tbl_name"/>
    <trim prefix="(" suffix=")" suffixOverrides=",">
    <#list fields as field>
      <if test="${field.name} != null">
        `${field.column}`,
      </if>
    </#list>
    </trim>
    <trim prefix="VALUES (" suffix=")" suffixOverrides=",">
    <#list fields as field>
      <if test="${field.name} != null">
        ${r'#{'}${field.name}, jdbcType=${field.jdbcType}},
      </if>
    </#list>
    </trim>
  </insert>

  <delete id="deleteById" parameterType="java.lang.<#list fields as field><#if field.primary>${field.type}</#if></#list>">
    DELETE FROM
    <include refid="tbl_name"/>
    WHERE <#list fields as field><#if field.primary>${field.column}=${r'#{'}${field.name}, jdbcType=${field.jdbcType}}</#if></#list>
  </delete>

  <delete id="batchDeleteByIds" parameterType="list">
    DELETE FROM
    <include refid="tbl_name"/>
    WHERE <#list fields as field><#if field.primary>${field.column}</#if></#list> IN
    <foreach collection="list" item="id" open="(" close=")" separator=",">
      ${r'#{id}'}
    </foreach>
  </delete>

  <update id="updateById" parameterType="${entityName}">
    UPDATE
    <include refid="tbl_name"/>
    <set>
    <#list fields as field>
      <#if !field.primary>
        <if test="${field.name} != null">
          `${field.column}` = ${r'#{'}${field.name}, jdbcType=${field.jdbcType}},
        </if>
      </#if>
    </#list>
    </set>
    WHERE <#list fields as field><#if field.primary>${field.column}=${r'#{'}${field.name}, jdbcType=${field.jdbcType}}</#if></#list>
  </update>

  <select id="selectById" resultMap="BaseResultMap" parameterType="java.lang.<#list fields as field><#if field.primary>${field.type}</#if></#list>">
    SELECT
    <include refid="full_columns"/>
    FROM
    <include refid="tbl_name"/>
    WHERE <#list fields as field><#if field.primary>${field.column}=${r'#{'}${field.name}, jdbcType=${field.jdbcType}}</#if></#list>
  </select>

  <select id="selectByIds" resultMap="BaseResultMap" parameterType="list">
    SELECT
    <include refid="full_columns"/>
    FROM
    <include refid="tbl_name"/>
    WHERE <#list fields as field><#if field.primary>${field.column}</#if></#list> IN
    <foreach collection="list" item="id" open="(" close=")" separator=",">
      ${r'#{id}'}
    </foreach>
  </select>

  <select id="selectIdsByOffset" resultMap="BaseResultMap">
    SELECT id
    FROM
    <include refid="tbl_name"/>
    LIMIT ${r'#{offset}, #{count}'}
  </select>

  <select id="count" resultType="java.lang.Long">
    SELECT COUNT(*)
    FROM
    <include refid="tbl_name"/>
  </select>

</mapper>