package com.hnkc.recognize.frame.tool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hnkc.recognize.frame.dict.DictItem;

/**
 * 枚举工具类
 * @author zhangguihua
 * @date 2020/09/29
 */
public class EnumTool {

    /**
     * 根据id翻译label

     * @param clz 枚举的类
     * @param id
     * @return
     */
    public static String getLabel(Class<?> clz, String id) {
        String key = clz.getName() + "." + id;
        String val = labelCache.get(key);
        if (val == null) {
            val = doGetLabel(clz, id);
            labelCache.put(key, val);
        }
        return val;
    }

    private static Map<String, String> labelCache = new HashMap<String, String>();

    private static String doGetLabel(Class<?> clz, String id) {
        try {
            Object[] objects = clz.getEnumConstants();
            Method getId = clz.getMethod("getId");
            Method getLabel = clz.getMethod("getLabel");
            for (Object obj : objects) {
                if (getId.invoke(obj).equals(id)) {
                    return (String) getLabel.invoke(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取枚举字典

     * @param type 枚举的类名
     * @return
     */
    public static List<DictItem> getEnumDictType(Class<?> clz) {
        String key = clz.getName();
        List<DictItem> val = dictCache.get(key);
        if (val == null) {
            val = doGetEnumDictType(clz);
            dictCache.put(key, val);
        }
        return val;
    }

    private static Map<String, List<DictItem>> dictCache = new HashMap<String, List<DictItem>>();

    private static List<DictItem> doGetEnumDictType(Class<?> clz) {
        List<DictItem> list = new ArrayList<>();
        try {
            Object[] objects = clz.getEnumConstants();
            Method getId = clz.getMethod("getId");
            Method getLabel = clz.getMethod("getLabel");
            for (Object obj : objects) {
                DictItem di = new DictItem();
                di.setId((String) getId.invoke(obj));
                di.setLabel((String) getLabel.invoke(obj));
                list.add(di);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("枚举字典不存在: " + clz.getName());
        }
        return list;
    }

}