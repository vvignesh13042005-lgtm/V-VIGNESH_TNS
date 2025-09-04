package com.customer;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class SecurityConfig implements WebMvcConfigurer  {



	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        // Allow cross-origin requests from your Angular app
	        registry.addMapping("/**") // Apply to all endpoints
	                .allowedOrigins("http://localhost:4200") // Replace with your Angular app's URL
	                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
	                .allowedHeaders("*")// Allow all headers
	                .allowCredentials(true); // Allow credentials (cookies, authorization headers, etc.
}}