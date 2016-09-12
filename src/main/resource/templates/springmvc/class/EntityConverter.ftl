package ${package};

import java.util.List;
import java.util.ArrayList;

import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.entity.${className};
${imports}

/**
${classDoc}
*/
public class ${className} {

    public static ${className} dto2Entity(${dtoName} dto){

        if(dto == null)
            return null;

        ${className} entity = new ${className}();

        <#list fields as field>
        entity.set${field.name?cap_first}(dto.get${field.name?cap_first}());
        </#list>

        return entity;
    }

    public static List<${className}> dto2Entity(List<${dtoName}> dtos){

        if(dtos == null)
            return null;

        List<${className}> entities = new ArrayList<>();

        for(${dtoName} dto : dtos){

            ${className} entity = new ${className}();

            <#list fields as field>
            vo.set${field.name?cap_first}(dto.get${field.name?cap_first}());
            </#list>

            entities.add(entity);
        }

        return entities;
    }

    public static ${dtoName} entity2DTO(${className} entity){

        if(form == null)
            return null;

        ${dtoName} dto = new ${dtoName}();

        <#list fields as field>
        dto.set${field.name?cap_first}(entity.get${field.name?cap_first}());
        </#list>

        return dto;
    }

    public static List<${dtoName}> entity2DTO(List<${className}> entity){

        if(forms == null)
            return null;

        List<${dtoName}> dtos = new ArrayList<>();

        for(${className} entity : entities){

            ${dtoName} dto = new ${dtoName}();

            <#list fields as field>
            dto.set${field.name?cap_first}(entity.get${field.name?cap_first}());
            </#list>

            dtos.add(dto);
        }

        return dtos;
    }
}