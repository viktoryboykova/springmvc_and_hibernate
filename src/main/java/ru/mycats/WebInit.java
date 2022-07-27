package ru.mycats;

import org.springframework.web.context.ContextLoaderListener;
import ru.mycats.config.HbmConfig;
import ru.mycats.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class, HbmConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));

        ServletRegistration.Dynamic registration = servletContext.addServlet("app", new DispatcherServlet(ctx));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
