package com.mds.jbot.stereotypes;

import me.ramswaroop.jbot.core.slack.Bot;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BotController
{
    Class<? extends Bot> value();
}
