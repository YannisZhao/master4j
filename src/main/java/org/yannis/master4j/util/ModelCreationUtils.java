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
package org.yannis.master4j.util;

import static freemarker.template.utility.StringUtil.capitalize;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.yannis.master4j.model.BeanInfo;

public final class ModelCreationUtils {

    private static final ThreadLocalRandom rand = ThreadLocalRandom.current();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private ModelCreationUtils() {
    }

    public static Object generateScript(BeanInfo beanInfo) throws Exception {
        StringBuilder script = new StringBuilder("return new ")
            .append(beanInfo.getEntityName()).append("()\n");

        for (org.yannis.master4j.model.Field field : beanInfo.getFieldList()) {
            if (field.isPrimary()) {
                continue;
            }
            String fieldType = field.getType();
            Object randomValue = getRandomValue(fieldType);
            script.append("\t\t\t.set").append(capitalizeFirst(field.getName())).append("(");
            if (fieldType.equals("String")) {
                script.append("\"").append(randomValue).append("\"");
            } else if (fieldType.equals("Timestamp")) {
                script.append("new Timestamp(").append(((Timestamp) randomValue).getTime()).append("L)");
            } else if (fieldType.equals("Date")) {
                script.append("new Date(").append(dateFormat.parse((String) randomValue).getTime()).append("L)");
            } else if (fieldType.equals("Float")) {
                script.append(randomValue).append("F");
            } else if (fieldType.equals("Long")) {
                script.append(randomValue).append("L");
            } else if (fieldType.equals("Double")) {
                script.append(randomValue).append("D");
            } else {
                script.append(randomValue);
            }
            script.append(")\n");
        }
        script.append(";");

        return script.toString();
    }

    public static final String generateScript(Class<?> clazz) throws Exception {
        StringBuilder script = new StringBuilder("return new ")
            .append(clazz.getSimpleName()).append("()\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Object randomValue = getRandomValue(field);
            script.append(".set").append(capitalize(field.getName())).append("(");
            if (field.getType().equals(String.class)) {
                script.append("\t\"").append(randomValue).append("\"");
            } else {
                script.append("\t").append(randomValue);
            }
            script.append(")\n");
        }

        return script.toString();
    }

    private static Object getRandomValue(String type) {
        if (type.equals("Integer")) {
            return rand.nextInt();
        }
        if (type.equals("Long")) {
            return rand.nextLong();
        }
        if (type.equals("Double")) {
            return rand.nextDouble();
        }
        if (type.equals("Float")) {
            return rand.nextFloat();
        }
        if (type.equals("String")) {
            return UUID.randomUUID().toString();
        }
        if (type.equals("BigInteger")) {
            return BigInteger.valueOf(rand.nextInt());
        }
        if (type.equals("BigDecimal")) {
            return BigDecimal.valueOf(rand.nextDouble());
        }
        if (type.equals("Timestamp")) {
            return new Timestamp(System.currentTimeMillis());
        }
        return null;
    }

    private static Object getRandomValue(Field field) throws Exception {
        Class<?> type = field.getType();

        if (type.isInterface()) {
            //Not support yet!
            return null;
        }

        if (type.isArray()) {
            Class<?> componentType = type.getComponentType();
            int bound = rand.nextInt();
            Object[] components = (Object[]) type.getConstructor().newInstance();
            for (int i = 0; i < bound; i++) {
                Object component = componentType.getConstructor().newInstance(getRandomTypeValue(componentType));
                components[i] = component;
            }
            return type.getConstructor().newInstance(components);
        }

        Object value = getRandomTypeValue(type);

        return value;
    }

    private static Object getRandomTypeValue(Class<?> type) {

        if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            return rand.nextInt();
        }
        if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            return rand.nextLong();
        }
        if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            return rand.nextDouble();
        }
        if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            return rand.nextFloat();
        }
        if (type.equals(String.class)) {
            return UUID.randomUUID().toString();
        }
        if (type.equals(BigInteger.class)) {
            return BigInteger.valueOf(rand.nextInt());
        }
        if (type.equals(BigDecimal.class)) {
            return BigDecimal.valueOf(rand.nextDouble());
        }
        if (type.equals(Timestamp.class)) {
            return new Timestamp(System.currentTimeMillis());
        }
        return null;
    }

    private static String capitalizeFirst(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        return new StringBuilder()
            .append(Character.toUpperCase(name.charAt(0)))
            .append(name.substring(1))
            .toString();
    }

    public static void main(String[] args) throws Exception {
        String script = generateScript(org.yannis.master4j.model.Field.class);
        System.out.println(script);
    }

}
