package com.maxPotential.MaxxEnergy.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class MvcConfig implements WebMvcConfigurer
{
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/resources/static")
				.addResourceLocations("/resources/");
	}
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/view").setViewName("view");

	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.enableContentNegotiation(new MappingJackson2JsonView());
		registry.jsp();
	}
}