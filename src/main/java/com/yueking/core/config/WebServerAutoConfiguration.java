package com.yueking.core.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class WebServerAutoConfiguration {
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        // 全局异常错误拦截
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/failed/400");
        ErrorPage errorPage401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/failed/401");
        ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/failed/403");
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/failed/404");
        ErrorPage errorPage415 = new ErrorPage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "/failed/415");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/failed/500");
        factory.addErrorPages(errorPage400,errorPage401,errorPage403,errorPage404,errorPage415,errorPage500);
        return factory;
    }
}
