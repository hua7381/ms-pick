package com.hnkc.recognize.frame;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置
 * 对应到application.yml中的config下
 * @author zhangguihua
 * @date 2020/09/29
 */
@Component
@ConfigurationProperties(prefix="config")
public class Config {
    /**
     * 查询word的sql语句
     */
    private String wordSql;

    public String getWordSql() {
        return wordSql;
    }

    public void setWordSql(String wordSql) {
        this.wordSql = wordSql;
    }
}
