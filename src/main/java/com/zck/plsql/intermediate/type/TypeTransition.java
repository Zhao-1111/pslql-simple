package com.zck.plsql.intermediate.type;

import java.util.EnumMap;
import java.util.Map;

public class TypeTransition extends EnumMap<Type, EnumMap<Type, Type>> {
    // 用以反序列化
    public TypeTransition() {
        super(Type.class);

    }
    public TypeTransition(Class<Type> keyType) {
        super(keyType);
    }

    public TypeTransition(EnumMap<Type, ? extends EnumMap<Type, Type>> m) {
        super(m);
    }

    public TypeTransition(Map<Type, ? extends EnumMap<Type, Type>> m) {
        super(m);
    }
}
