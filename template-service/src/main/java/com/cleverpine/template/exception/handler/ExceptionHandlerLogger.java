package com.cleverpine.template.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExceptionHandlerLogger {
    public void error(String s, Throwable throwable) {
        log.error(s, throwable);
    }
}
