package com.toptal.steppin.webapp.config;

import com.toptal.steppin.core.config.CoreConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

/**
 * WebConfig Class.
 * User: ggomes
 */
@Configuration
@ComponentScan(value={"com.toptal.steppin.webapp.controller"})
@Import(value = {CoreConfig.class, WebSecurity.class})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public ViewResolver viewResolver() {
        TilesViewResolver viewResolver = new TilesViewResolver();
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
