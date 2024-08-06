package com.zck.plsql.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zck.plsql.intermediate.type.TypeTransition;
import com.zck.plsql.intermediate.type.Type;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static String map2Json(TypeTransition typetTansition) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(typetTansition);
        Mylogger.debug(json);
        return json;
    }

    public static TypeTransition json2map(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        TypeFactory typeFactory = mapper.getTypeFactory();

        TypeTransition typeTransition = mapper.readValue(json,
                typeFactory.constructMapLikeType(
                        TypeTransition.class,
                        typeFactory.constructType(Type.class),
                        typeFactory.constructMapLikeType(
                                TypeTransition.class,
                                typeFactory.constructType(Type.class),
                                typeFactory.constructType(Type.class)
                        )
                )
        );
        return typeTransition;
    }

    public static String jsonFile2String(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 读取JSON文件
        File jsonFile = new File(path);
        // 将JSON文件转换为字符串
        return mapper.readTree(jsonFile).toString();

    }
}
