package com.renault.guide;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableWebMvc
public class WebConfig extends WebMvcAutoConfiguration {
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler( //
//				"/webjars/**", //
//				"/img/**", //
//				"/css/**", //
//				"/js/**", //
//				"/favicon.ico"
//		)
//				.addResourceLocations( //
//						"classpath:/META-INF/resources/webjars/", //
//						"classpath:/static/img/", //
//						"classpath:/static/css/", //
//						"classpath:/static/js/", //
//						"classpath:/static/" //
//				);
//	}
}
