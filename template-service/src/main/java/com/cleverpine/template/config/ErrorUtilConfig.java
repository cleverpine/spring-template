package com.cleverpine.template.config;

import com.cleverpine.cpspringerrorutil.mapper.BaseExceptionTypeMapper;
import com.cleverpine.cpspringerrorutil.mapper.ExceptionTypeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorUtilConfig {

    @Bean
    public ExceptionTypeMapper exceptionTypeMapper() {
        return new BaseExceptionTypeMapper();
    }
}
