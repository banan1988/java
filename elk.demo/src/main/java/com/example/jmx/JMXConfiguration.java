package com.example.jmx;

import org.jminix.console.servlet.MiniConsoleServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class JMXConfiguration {

    @Bean
    public ServletRegistrationBean jminixServlet() {
        ServletRegistrationBean servletBean = new ServletRegistrationBean();
        servletBean.addUrlMappings("/jmx/*");
        servletBean.setServlet(new MiniConsoleServlet());
        return servletBean;
    }

    // one needs to exclude this filter for jminix to work
    @Bean
    public FilterRegistrationBean excludeDefaultFilter(HiddenHttpMethodFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

}
