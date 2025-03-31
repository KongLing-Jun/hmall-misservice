package com.hmall.cart.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : SwaggerConfig
 * @description : [配置swagger文档类]
 * @createTime : [2025/3/6 11:24]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/3/6 11:24]
 * @updateRemark : [v1.0]
 */
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Value("${server.port:}")
    private String serverPort;

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean(value = "createRestApi")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("cart-service") // 设置一个唯一的组名
                .apiInfo(apiInfo())
                .select()
                // 指定扫描的包路径来生成接口文档，可以根据实际情况修改
                .apis(RequestHandlerSelectors.basePackage("com.hmall.cart.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("黑马条目微服务拆分") // 文档标题
                .description("黑马条目微服务拆分接口测试") // 文档描述
                .version("1.0") // 文档版本
                .build();
    }

    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 启动时打印Knife4j文档地址
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Swagger文档地址：http://localhost:{}{}/doc.html", serverPort, contextPath);
    }
}
