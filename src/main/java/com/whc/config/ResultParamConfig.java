package com.whc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whc on 2017/7/12.
 */
@Configuration
public class ResultParamConfig {
    public ResultParamConfig() {
    }

    @Bean(
            name = {"jacksonObjectMapper"}
    )
    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(MappingJackson2HttpMessageConverter jsonConverter, MappingJackson2XmlHttpMessageConverter xmlConverter) {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> messageConvertersList = new ArrayList();
        messageConvertersList.add(jsonConverter);
        requestMappingHandlerAdapter.setMessageConverters(messageConvertersList);
        return requestMappingHandlerAdapter;
    }

    @Bean(
            name = {"jsonConverter"}
    )
    public MappingJackson2HttpMessageConverter jsonConverter(ObjectMapper jacksonObjectMapper) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        jsonConverter.setPrettyPrint(Boolean.TRUE.booleanValue());
        jsonConverter.setObjectMapper(jacksonObjectMapper);
        return jsonConverter;
    }

}

