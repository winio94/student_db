package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Michał on 2016-11-27.
 */
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final String RESOURCES = "resources";
    private static final String MVC_VIEWS_PREFIX = "/WEB-INF/pages/";
    private static final String MVC_VIEWS_SUFFIX = ".jsp";
    private static final String SLASH = "/";
    private static final String ANY_NAME = "**";

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(MVC_VIEWS_PREFIX);
        resolver.setSuffix(MVC_VIEWS_SUFFIX);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(SLASH + RESOURCES + SLASH + ANY_NAME)
                .addResourceLocations(SLASH + RESOURCES + SLASH);
    }
}