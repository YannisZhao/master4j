package org.yannis.master4j.core.engine.support.freemarker;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import org.yannis.master4j.entity.Field;

import java.util.ArrayList;
import java.util.List;

public class UnionPK implements TemplateMethodModel {
    @Override
    public Object exec(List list) throws TemplateModelException {
        try {
            List<Field> fields = (List<Field>) list.get(0);
            List<Field> pkList = new ArrayList<>();
            for (Field field : fields){
                if(field.isPrimary()){
                    pkList.add(field);
                }
            }
            return pkList;
        }catch (Exception e){
            return new ArrayList<Field>();
        }
    }
}
