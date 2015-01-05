package org.whut.platform.fundamental.util.json;

import com.google.common.collect.Maps;
import org.whut.platform.fundamental.util.xss.XSSUtil;

import java.util.Map;

public final class JsonResultUtils {

    public enum Code {
        SUCCESS(200, "操作成功！"), ERROR(500, "对不起，操作出错！"), NOTFOUND(404,
                "对不起，您请求的资源不存在！"), DUPLICATE(302, "重复操作！"), NOPERM(403,
                "对不起，您没有进行此项操作的权限"), ILLEGA(422, "请求服务参数异常"),MONGOUNCONNECT(515,"Mongo未连接!"),QUESTION(505,"出现问题,需要补全操作!");
        private final int code;
        private final String message;

        private Code(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public int getCode() {
            return code;
        }

    }
    private JsonResultUtils() {
        throw new Error("Utility classes should not instantiated!");
    }

    public static Map<String, Object> getCodeAndMesMap(Integer code,
                                                       String message) {
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("message", message);
        return resultMap;
    }

    public static Map<String, Object> getCodeAndMesMapAsDefault(Code status) {
        return getCodeAndMesMap(status.code, status.message);
    }

    public static <T> Map<String, Object> getObjectResultMap(Integer code,
                                                             Object object, String message) {
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("data", object);
        resultMap.put("message", message);
        return resultMap;
    }
    /*
    新加入
     */
    public static <T> Map<String, Object> getObjectStrResultMap(Integer code,
                                                             Object object, String str) {
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", code);
        resultMap.put("data", object);
        resultMap.put("str", str);
        return resultMap;
    }

    public static <T> Map<String, Object> getObjectResultMapAsDefault(
            Object object, Code status) {
        return getObjectResultMap(status.code, object, status.message);
    }
    /*
    新加入
     */
    public static <T> Map<String, Object> getObjectStrResultMapAsDefault(
            Object object, Code status) {
        return getObjectStrResultMap(status.code, object, status.message);
    }

    public static <T> String getObjectResultAsJsonP(String cb, Object object,
                                                    Code status) {
        String result = getObjectResultByStringAsDefault(object, status);
        return buildJsonPResult(cb, result);
    }

    private static String buildJsonPResult(String cb, String result) {
        return new StringBuffer(XSSUtil.htmlEncode(cb)).append("(")
                .append(result).append(")").toString();
    }

    /**
     * 根据必要的参数获取jsonp返回结果， 补充缺失的接口
     *
     * @param <T>
     * @param cb
     * @param object
     * @param status
     * @param message
     * @return
     */
    public static <T> String getObjectResultAsJsonP(String cb, Object object,
                                                    int status, String message) {
        String result = getObjectResultByString(status, object, message);
        return buildJsonPResult(cb, result);
    }

    public static <T> String getObjectResultAsJsonP(String cb, Code status) {
        String result = getObjectResultByStringAsDefault(null, status);
        return buildJsonPResult(cb, result);
    }

    public static <T> String getObjectResultAsJsonP(String cb, int code,
                                                    String message) {
        String result = getObjectResultByString(code, null, message);
        return buildJsonPResult(cb, result);
    }

    public static String getCodeAndMesByString(Integer code, String message) {
        return JsonMapper.buildNormalMapper().toJson(
                getCodeAndMesMap(code, message));
    }

    public static String getCodeAndMesByStringAsDefault(Code status) {
        return JsonMapper.buildNormalMapper().toJson(
                getCodeAndMesMapAsDefault(status));
    }

    public static String getObjectResultByString(Integer code, Object object,
                                                 String message) {
        return JsonMapper.buildNormalMapper().toJson(
                getObjectResultMap(code, object, message));
    }

    public static String getObjectResultByStringAsDefault(Object object,
                                                          Code status) {
        return JsonMapper.buildNormalMapper().toJson(
                getObjectResultMapAsDefault(object, status));
    }
    /*
    新加入的
     */
    public static String getObjectStrResultByStringAsDefault(Object object,
                                                             Integer code,String str) {
        return JsonMapper.buildNormalMapper().toJson(
                getObjectStrResultMap(code, object,str));
    }


}
