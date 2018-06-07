package com.haolyy.haolyy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;

public class JsonUtils {
    public static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gb = new GsonBuilder();
            gson = gb.create();
        }
        return gson;
    }

    /**
     * 将Java对象转换成json字符串
     *
     * @param object
     * @return
     */
    public static <T> String toJson(T object) {
        return getGson().toJson(object);
    }

    /**
     * 将json字符串转成JavaBean对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        return getGson().fromJson(jsonStr, clazz);
    }

    /**
     * 将json对象转换为实体对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(JsonObject json, Class<T> clz) throws JsonSyntaxException {
        return getGson().fromJson(json, clz);
    }

    /**
     * 将json字符串转换为对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Type type) throws JsonSyntaxException {
        return getGson().fromJson(json, type);
    }

    public static String getObjectForName(final Object json, final String name) {
        String result = null;
        if (json != null) {
            try {
                JSONTokener jsonParser = new JSONTokener(json.toString());
                JSONObject status = (JSONObject) jsonParser.nextValue();
                Object obj = status.get(name);
                if (obj != null) {
                    result = obj.toString();
                }
                jsonParser = null;
                status = null;
            } catch (Throwable e) {
                result = null;
            }
        }
        return result;
    }

}
