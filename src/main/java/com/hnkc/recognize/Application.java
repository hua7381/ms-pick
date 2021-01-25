package com.hnkc.recognize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Springboot程序启动类
 * @author zhangguihua
 * @date 2020/09/29
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class Application {

    static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("starting...");
        SpringApplication.run(Application.class, args);
        logger.info("start success.");
    }

}
