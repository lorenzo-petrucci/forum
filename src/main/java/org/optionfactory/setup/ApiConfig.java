package org.optionfactory.setup;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.optionfactory.Main;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.util.List;

@EnableWebMvc
@PropertySource(value = "classpath:project.properties", encoding = "UTF-8")
@PropertySource(value = "file:/opt/${project.name}/conf/git.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
@PropertySource(value = "file:${user.home}/.${project.name}.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
@PropertySource(value = "file:/opt/${project.name}/conf/project.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
@ComponentScan(basePackageClasses = Main.class, includeFilters = @Filter(type = FilterType.ANNOTATION, classes = Controller.class))
@Configuration
public class ApiConfig implements WebMvcConfigurer {
    @Bean
    public ObjectMapper objectMapper() {
        final var om = new Jackson2ObjectMapperBuilder()
                .failOnEmptyBeans(false)
                .autoDetectFields(true)
                .featuresToDisable(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule(), new Jdk8Module())
                .build();

        om.configOverride(BigDecimal.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
        return om;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new ResourceHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper()));
    }

}
