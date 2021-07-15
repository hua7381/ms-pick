package com.hnkc.recognize.frame.dict;

/**
 * 数据字典
 * 系统启动后，加载字典数据并缓存到本单例中
 * @author zhangguihua
 * @date 2020/09/29
 */
public class Dict {

    /**
     * 单例模式
     */
    private Dict() {};
    private static Dict instance;
    public static Dict getInstance() {
        if (instance == null) {
            instance = new Dict();
        }
        return instance;
    }

    /**
     * 部门
     */
    private DictType dept;

    public DictType getDept() {
        return dept;
    }
    public void setDept(DictType dept) {
        this.dept = dept;
    }

}
