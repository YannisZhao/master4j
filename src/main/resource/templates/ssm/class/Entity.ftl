<#include "../../lib/declare.ftl"/>
package ${package};

${imports}

/**
 * ${classDoc}
 */
public class ${className} {

<#list fields as field>
    /** ${field.comment} */
    private ${field.type} ${field.name};
</#list>

<#list fields as field>
    public ${field.type} get${field.name?cap_first}(){
        return ${field.name};
    }
    public ${className} set${field.name?cap_first}(${field.type} ${field.name}){
        this.${field.name} = ${field.name};
        return this;
    }
</#list>

    @Override
    public String toString(){
        return "{"+
        <#list fields as field>
            "${field.name}:" + this.${field.name}<#if field_has_next> +"," </#if> +
        </#list>
        "}";
    }
}