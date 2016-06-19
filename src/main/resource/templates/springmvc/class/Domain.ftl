package ${package}

import java.io.Serializable;
${imports}
/**
${classDoc}
*/
public class ${className} implements Serializable {

<#list fields as field>
    /** ${field.comment} */
    private ${field.type} ${field.name};
</#list>

<#list fields as field>
    public ${field.type} get${field.name?cap_first}(){
        return ${field.name};
    }
    public void set${field.name?cap_first}(${field.type} ${field.name}){
        this.${field.name} = ${field.name};
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