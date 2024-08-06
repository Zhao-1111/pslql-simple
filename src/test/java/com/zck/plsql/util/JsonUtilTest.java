package com.zck.plsql.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zck.plsql.intermediate.type.Type;

import com.zck.plsql.intermediate.type.TypeTransition;
import org.junit.Test;

import java.io.IOException;
import java.util.EnumMap;

public class JsonUtilTest {
    @Test
    public void test1() throws JsonProcessingException {
        TypeTransition typetTansition = new TypeTransition(Type.class);
        typetTansition.put(Type.PLS_INTEGER, new EnumMap<Type, Type>(Type.class) {{
            put(Type.PLS_INTEGER, Type.PLS_INTEGER);
            put(Type.LONG, Type.LONG);
            put(Type.NUMBER, Type.NUMBER);
        }});

        typetTansition.put(Type.LONG, new EnumMap<Type, Type>(Type.class) {{
            put(Type.PLS_INTEGER, Type.LONG);
            put(Type.LONG, Type.LONG);
            put(Type.NUMBER, Type.NUMBER);
        }});
        JsonUtil.map2Json(typetTansition);
    }

    @Test
    public void test2() throws JsonProcessingException {
        TypeTransition typeTransition = new TypeTransition(Type.class);
        typeTransition.put(Type.PLS_INTEGER, new EnumMap<Type, Type>(Type.class) {{
            put(Type.PLS_INTEGER, Type.PLS_INTEGER);
            put(Type.LONG, Type.LONG);
            put(Type.NUMBER, Type.NUMBER);
        }});

        typeTransition.put(Type.LONG, new EnumMap<Type, Type>(Type.class) {{
            put(Type.PLS_INTEGER, Type.LONG);
            put(Type.LONG, Type.LONG);
            put(Type.NUMBER, Type.NUMBER);
        }});
        String json = JsonUtil.map2Json(typeTransition);
        TypeTransition typeTransition1 = JsonUtil.json2map(json);
        JsonUtil.map2Json(typeTransition);
    }

    @Test
    public void test3() throws JsonProcessingException {
        String json = "{\n"
                + "  \"LONG\": {\n"
                + "    \"NUMBER\": \"NUMBER\",\n"
                + "    \"LONG\": \"LONG\",\n"
                + "    \"PLS_INTEGER\": \"LONG\"\n"
                + "  },\n"
                + "  \"PLS_INTEGER\": {\n"
                + "    \"NUMBER\": \"NUMBER\",\n"
                + "    \"LONG\": \"LONG\",\n"
                + "    \"PLS_INTEGER\": \"PLS_INTEGER\"\n"
                + "  }\n"
                + "}\n";
        TypeTransition typeTransition = JsonUtil.json2map(json);
        JsonUtil.map2Json(typeTransition);
    }

    @Test
    public void test4() throws IOException {
        String json = JsonUtil.jsonFile2String(
                "/Users/zckoon/workspace/procedure-executor/zck/plsql-simple/src/typetransition/output_json/assign.json");
        TypeTransition typeTransition = JsonUtil.json2map(json);
        JsonUtil.map2Json(typeTransition);
    }
}
