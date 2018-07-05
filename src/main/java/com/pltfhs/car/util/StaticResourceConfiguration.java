package com.pltfhs.car.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/media/**").addResourceLocations("src/main/resources/static/media")
                .setCachePeriod(0);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        final List<MediaType> list = new ArrayList<>();
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        list.add(MediaType.APPLICATION_PDF);
        arrayHttpMessageConverter.setSupportedMediaTypes(list);
        converters.add(arrayHttpMessageConverter);

        super.configureMessageConverters(converters);
    }
}
