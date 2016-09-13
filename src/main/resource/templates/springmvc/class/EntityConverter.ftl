package ${package};

import java.util.List;
import java.util.ArrayList;

import ${basePackageName}.api.dto.${dtoName};
import ${basePackageName}.domain.${entityName};
${imports}

/**
${classDoc}
*/
public class ${className} {

    public static ${entityName} dto2Entity(${dtoName} dto){

        if(dto == null)
            return null;

        ${entityName} entity = new ${entityName}();

        <#list fields as field>
        entity.set${field.name?cap_first}(dto.get${field.name?cap_first}());
        </#list>

        return entity;
    }

    public static List<${entityName}> dto2Entity(List<${dtoName}> dtos){

        if(dtos == null)
            return null;

        List<${entityName}> entities = new ArrayList<>();

        for(${dtoName} dto : dtos){

            ${entityName} entity = new ${entityName}();

            <#list fields as field>
            entity.set${field.name?cap_first}(dto.get${field.name?cap_first}());
            </#list>

            entities.add(entity);
        }

        return entities;
    }

    public static ${dtoName} entity2DTO(${entityName} entity){

        if(entity == null)
            return null;

        ${dtoName} dto = new ${dtoName}();

        <#list fields as field>
        dto.set${field.name?cap_first}(entity.get${field.name?cap_first}());
        </#list>

        return dto;
    }

    public static List<${dtoName}> entity2DTO(List<${entityName}> entities){

        if(entities == null)
            return null;

        List<${dtoName}> dtos = new ArrayList<>();

        for(${entityName} entity : entities){

            ${dtoName} dto = new ${dtoName}();

            <#list fields as field>
            dto.set${field.name?cap_first}(entity.get${field.name?cap_first}());
            </#list>

            dtos.add(dto);
        }

        return dtos;
    }
}