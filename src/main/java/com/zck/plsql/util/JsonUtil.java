package com.zck.plsql.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zck.plsql.intermediate.TypeTansition;
import com.zck.plsql.intermediate.type.Type;

public class JsonUtil {
    public static String map2Json(TypeTansition typetTansition) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(typetTansition);
        Mylogger.debug(json);
        return json;
    }

    public static TypeTansition json2map(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();

        TypeTansition typeTansition = mapper.readValue(json,
                typeFactory.constructMapLikeType(
                        TypeTansition.class,
                        typeFactory.constructType(Type.class),
                        typeFactory.constructMapLikeType(
                                TypeTansition.class,
                                typeFactory.constructType(Type.class),
                                typeFactory.constructType(Type.class)
                        )
                )
        );
        return typeTansition;
    }
}
