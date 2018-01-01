package com.mds.jbot;

import com.mds.jbot.stereotypes.BotController;
import me.ramswaroop.jbot.core.slack.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BotControllerAnnotationProcessor
{
    private static final Logger logger = LoggerFactory.getLogger(BotControllerAnnotationProcessor.class);

    @Autowired
    private ApplicationContext applicationContext;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        Map<String, Object> controllers = event.getApplicationContext()
            .getBeansWithAnnotation(BotController.class);

        controllers.forEach((name, bean) -> {
            BotController annotation = bean.getClass().getAnnotation(BotController.class);
            logger.debug("Register bot controller for class {}", bean.getClass().getName());
            Bot bot = applicationContext.getBean(annotation.value());
            bot.registerController(bean);
        });
    }
}
