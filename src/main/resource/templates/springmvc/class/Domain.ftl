${package}

${imports}

/**
    ${classDoc}
*/
public Class ${className} {

     <#list properties as prop>
        private ${prop.type} ${prop.name};
      </#list>

      <#list properties as prop>
        public ${prop.type} get${prop.name?cap_first}(){
          return ${prop.name};
        }
        public void set${prop.name?cap_first}(${prop.type} ${prop.name}){
          this.${prop.name} = ${prop.name};
        }
      </#list>

    @Override
    public String toString(){
        ${toStringBody}
    }
}