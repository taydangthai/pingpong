package vn.company.prjname.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ApplicationContextConfig implements WebMvcConfigurer {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/bootstrap/**").addResourceLocations("/WEB-INF/bootstrap/")
				.setCachePeriod(Integer.MAX_VALUE);
		registry.addResourceHandler("/dist/**").addResourceLocations("/WEB-INF/dist/")
				.setCachePeriod(Integer.MAX_VALUE);
		registry.addResourceHandler("/plugins/**").addResourceLocations("/WEB-INF/plugins/")
				.setCachePeriod(Integer.MAX_VALUE);
		registry.addResourceHandler("/views/**").addResourceLocations("/WEB-INF/views/")
				.setCachePeriod(Integer.MAX_VALUE);
		registry.addResourceHandler("/jQuery/**").addResourceLocations("/WEB-INF/jQuery/")
				.setCachePeriod(Integer.MAX_VALUE);
		registry.addResourceHandler("/jQuery-ui/**").addResourceLocations("/WEB-INF/jquery-ui-1.12.1.custom/")
				.setCachePeriod(Integer.MAX_VALUE);
		registry.addResourceHandler("/icon/**").addResourceLocations("/WEB-INF/icon/")
				.setCachePeriod(Integer.MAX_VALUE);

	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF8");
		templateResolver.setCacheable(false);
		return templateResolver;
	}
}
