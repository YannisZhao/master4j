<#include "../../lib/declare.ftl"/>
package ${package};

import java.util.List;
import java.util.ArrayList;

import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.web.vo.${voName};
import ${basePackageName}.web.form.${formName};
${imports}

/**
 * ${classDoc}
 */
public class ${className} {

    public static ${voName} toVO(final ${dtoName} dto){

        if (dto == null) {
            return null;
        }

        ${voName} vo = new ${voName}();

        <#list fields as field>
        vo.set${field.name?cap_first}(dto.get${field.name?cap_first}());
        </#list>

        return vo;
    }

    public static List<${voName}> toVO(final List<${dtoName}> dtos){

        if (dtos == null) {
            return null;
        }

        List<${voName}> vos = new ArrayList<>();

        for(${dtoName} dto : dtos){

            vos.add(toVO(dto));
        }

        return vos;
    }

    public static ${dtoName} parseForm(final ${formName} form){

        if (form == null) {
            return null;
        }

        ${dtoName} dto = new ${dtoName}();

        <#list fields as field>
        dto.set${field.name?cap_first}(form.get${field.name?cap_first}());
        </#list>

        return dto;
    }

    public static List<${dtoName}> parseForm(final List<${formName}> forms){

        if (forms == null) {
            return null;
        }

        List<${dtoName}> dtos = new ArrayList<>();

        for(${formName} form : forms){

            dtos.add(parseForm(form));
        }

        return dtos;
    }
}