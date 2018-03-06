/**
 * Copyright © 2015 - 2025 Yannis Zhao (zhaoyjun0222@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yannis.master4j.core.engine.support.freemarker;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import java.util.ArrayList;
import java.util.List;
import org.yannis.master4j.model.Field;

public class UnionPK implements TemplateMethodModel {

    @Override
    public Object exec(List list) throws TemplateModelException {
        try {
            List<Field> fields = (List<Field>) list.get(0);
            List<Field> pkList = new ArrayList<>();
            for (Field field : fields) {
                if (field.isPrimary()) {
                    pkList.add(field);
                }
            }
            return pkList;
        } catch (Exception e) {
            return new ArrayList<Field>();
        }
    }
}
