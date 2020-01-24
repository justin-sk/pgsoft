package com.pgsoft.web.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ResolverConfiguration implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        final PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver();
        pageableHandlerMethodArgumentResolver.setPageParameterName("pageNumber");
        pageableHandlerMethodArgumentResolver.setPageParameterName("pageSize");
        argumentResolvers.add(pageableHandlerMethodArgumentResolver);
        final AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver = new AuthenticationPrincipalArgumentResolver();
        argumentResolvers.add(authenticationPrincipalArgumentResolver);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        final DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }

    @Bean
    public Module getBigDecimalModule() {
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(BigDecimal.class, new JsonDeserializer<BigDecimal>() {
            @Override
            public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return new BigDecimal(jsonParser.getDecimalValue().stripTrailingZeros().toPlainString());
            }
        });
        return simpleModule;
    }
}
