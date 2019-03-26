package com.fsd.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.fsd.springmvc" })
@PropertySource(value = { "classpath:application.properties" })
public class WebAppConfig extends WebMvcConfigurerAdapter {
	
	
	@Autowired
    private Environment env;

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	 @Override
	 public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**","js/**")
        	.addResourceLocations("classpath:/static/css/","classpath:/static/js");
	 }
	
	
 	/*
     * Configure MessageSource to provide internationalized messages
     *
     */
	 
	  @Bean
	  public MessageSource messageSource() {
		  ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	      messageSource.setBasename("messages");
	      return messageSource;
	  }
	  
	  @Bean
	  public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		  return new PropertySourcesPlaceholderConfigurer();
	  }
	  
	  @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
	        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
	        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
	        return dataSource;
	    }
	 
	    @Bean
	    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        jdbcTemplate.setResultsMapCaseInsensitive(true);
	        return jdbcTemplate;
	    }

}
