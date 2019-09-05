package com.lee.landon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Lee97
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    return new InternalResourceViewResolver(".jsp", "/WEB-INF/views");
  }
  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/resources");
    registry.addResourceHandler("/js/**").addResourceLocations("WEB-INF/resources/js/");
  }
  @Bean
   CommonsMultipartResolver multipartResolver(){
   CommonsMultipartResolver multipartResolver= new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(6000000);
    return multipartResolver;
  }
}
