package com.zck.plsql.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zck.plsql.intermediate.type.Type;

import com.zck.plsql.intermediate.TypeTansition;
import org.junit.Test;

import java.util.EnumMap;

public class JsonUtilTest {
    @Test
    public void test1() throws JsonProcessingException {
        TypeTansition typetTansition = new TypeTansition(Type.class);
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
        TypeTansition typeTansition = new TypeTansition(Type.class);
        typeTansition.put(Type.PLS_INTEGER, new EnumMap<Type, Type>(Type.class) {{
            put(Type.PLS_INTEGER, Type.PLS_INTEGER);
            put(Type.LONG, Type.LONG);
            put(Type.NUMBER, Type.NUMBER);
        }});

        typeTansition.put(Type.LONG, new EnumMap<Type, Type>(Type.class) {{
            put(Type.PLS_INTEGER, Type.LONG);
            put(Type.LONG, Type.LONG);
            put(Type.NUMBER, Type.NUMBER);
        }});
        String json = JsonUtil.map2Json(typeTansition);
        TypeTansition typeTansition1 = JsonUtil.json2map(json);
        JsonUtil.map2Json(typeTansition);
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
        TypeTansition typeTansition = JsonUtil.json2map(json);
        JsonUtil.map2Json(typeTansition);
    }
}
