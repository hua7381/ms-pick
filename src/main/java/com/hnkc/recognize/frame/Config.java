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
     * 网页标题
     */
    private String webTitle;
    /**
     * 网页标题
     */
    private String wordSql;

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWordSql() {
        return wordSql;
    }

    public void setWordSql(String wordSql) {
        this.wordSql = wordSql;
    }
}
