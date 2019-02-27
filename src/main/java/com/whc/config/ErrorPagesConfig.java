package com.whc.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author wang_haichun
 * @date 2018/11/12
 */
@Configuration
public class ErrorPagesConfig {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                //状态码 ：HttpStatus.NOT_FOUND（404）       错误页面的存储路径：/WEB-INF/views/common/error_404.jsp
                ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/WEB-INF/error/400.jsp");
                ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/WEB-INF/error/400.jsp");
                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/error/404.jsp");
                ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/WEB-INF/error/500.jsp");
                container.addErrorPages(errorPage400,errorPage403, errorPage404, errorPage500);
            }
        };
    }
}
