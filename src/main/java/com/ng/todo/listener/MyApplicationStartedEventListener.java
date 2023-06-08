package com.ng.todo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * spring boot 启动监听类
 *
 * @author liaokailin
 * @version $Id: MyApplicationStartedEventListener.java, v 0.1 2015年9月2日 下午11:06:04 liaokailin Exp $
 */
@Component
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    //spring boot启动开始时执行的事件
    private Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SpringApplication app = event.getSpringApplication();
        // 不显示banner信息
        app.setBannerMode(Banner.Mode.CONSOLE);
        logger.info("==MyApplicationStartedEventListener==");
    }
}
