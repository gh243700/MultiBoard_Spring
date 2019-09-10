package com.lee.landon.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/** @author Lee97 */
public class WebInitializerConfig implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {

    AnnotationConfigWebApplicationContext applicationContext =
        new AnnotationConfigWebApplicationContext();
    applicationContext.register(ApplicationConfig.class);
    applicationContext.setConfigLocation("com.lee.landon.config");
    servletContext.addListener(new ContextLoaderListener(applicationContext));


    FilterRegistration.Dynamic filterRegistration =
        servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
    filterRegistration.addMappingForUrlPatterns(null, false, "/*");
    filterRegistration.setInitParameter("encoding", "UTF-8");
    filterRegistration.setInitParameter("forceEncoding", "true");

    AnnotationConfigWebApplicationContext webApplicationContext =
        new AnnotationConfigWebApplicationContext();
    webApplicationContext.register(WebConfig.class);

    ServletRegistration.Dynamic dispatcher =
        servletContext.addServlet("appServlet", new DispatcherServlet(webApplicationContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
  }
}
