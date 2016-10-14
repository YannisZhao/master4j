<#macro compress_single_line>
    <#local captured><#nested></#local>
${ captured?replace("\\r\\n|\\n|\\r", "", "rm") }
</#macro>
<#assign idParams>
<#list fields as field>
<#if field.primary>
${field.type} ${field.name},
</#if>
</#list>
</#assign>
<#assign idParams><@compress_single_line>${idParams?substring(0, idParams?length-2)}</@compress_single_line></#assign>
<#assign idValues>
    <#list fields as field>
        <#if field.primary>
        ${field.name},<#lt>
        </#if>
    </#list>
</#assign>
<#assign idValues>${idValues?substring(0, idValues?length-2)}</#assign>
<#assign batchRemoveParams>
    <#if pks?size gt 1>
    List<Map<String, String>> pks<#lt>
    <#else>
    String[] ids<#lt>
    </#if>
</#assign>