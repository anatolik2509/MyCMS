package ru.itis.antonov.cms.config;

import lombok.SneakyThrows;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class WebInitializer implements WebApplicationInitializer {

    private final String TMP_FOLDER = "/tmp";
    private final int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @SneakyThrows
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        PropertySource propertySource = new ResourcePropertySource("classpath:application.properties");
        springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("spring.profile"));

        springWebContext.register(ApplicationConfig.class);
        servletContext.addListener(new ContextLoaderListener(springWebContext));

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        FilterRegistration.Dynamic filterRegistration = servletContext
                .addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
//                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
//
//        dispatcherServlet.setMultipartConfig(multipartConfigElement);
    }
}
