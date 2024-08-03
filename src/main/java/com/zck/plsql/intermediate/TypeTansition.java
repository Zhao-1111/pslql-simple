package com.zck.plsql.intermediate;

import com.zck.plsql.intermediate.type.Type;

import java.util.EnumMap;
import java.util.Map;

public class TypeTansition extends EnumMap<Type, EnumMap<Type, Type>> {
    // 用以反序列化
    public TypeTansition() {
        super(Type.class);

    }
    public TypeTansition(Class<Type> keyType) {
        super(keyType);
    }

    public TypeTansition(EnumMap<Type, ? extends EnumMap<Type, Type>> m) {
        super(m);
    }

    public TypeTansition(Map<Type, ? extends EnumMap<Type, Type>> m) {
        super(m);
    }
}
