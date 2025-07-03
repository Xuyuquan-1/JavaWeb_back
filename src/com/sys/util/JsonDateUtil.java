package com.sys.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class JsonDateUtil {
    private static final SerializeConfig DATE_CONFIG = new SerializeConfig();

    static {
        DATE_CONFIG.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, DATE_CONFIG, SerializerFeature.DisableCircularReferenceDetect);
    }
}