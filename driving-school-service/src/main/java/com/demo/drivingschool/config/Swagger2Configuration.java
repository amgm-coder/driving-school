package com.demo.drivingschool.config;

import com.demo.drivingschool.config.properties.SwaggerProperties;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author
 * 
 */
@Configuration
@AllArgsConstructor
public class Swagger2Configuration {

    private final SwaggerProperties swaggerProperties;

    // 扫描多包时，包路径的拆分符,分号
    private static final String SPLIT_COMMA = ",";

    // 扫描多包时，包路径的拆分符,逗号
    private static final String SPLIT_SEMICOLON = ";";

    // Swagger忽略的参数类型
    private static Class<?>[] IGNORED_PARAMETER_TYPES = new Class[]{
            ServletRequest.class,
            ServletResponse.class,
            HttpServletRequest.class,
            HttpServletResponse.class,
            HttpSession.class,
            ApiIgnore.class
    };

    @Bean
    public Docket createRestApi() {
        // 获取需要扫描的包
        String[] basePackages = getBasePackages();
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select();
        // 如果扫描的包为空，则默认扫描类上有@Api注解的类
        if (ArrayUtils.isEmpty(basePackages)) {
            apiSelectorBuilder.apis(RequestHandlerSelectors.withClassAnnotation(Api.class));
        } else {
            // 扫描指定的包
            apiSelectorBuilder.apis(basePackage(basePackages));
        }
        Docket docket = apiSelectorBuilder.paths(PathSelectors.any())
                .build()
                .enable(swaggerProperties.isEnable())
                .ignoredParameterTypes(IGNORED_PARAMETER_TYPES);
        return docket;
    }

    /**
     * 获取apiInfo
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl(swaggerProperties.getUrl())
                .contact(new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(), swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

    /**
     * 获取扫描的包
     *
     * @return
     */
    public String[] getBasePackages() {
        String basePackage = swaggerProperties.getBasePackage();
        if (StringUtils.isBlank(basePackage)) {
            throw new RuntimeException("Swagger basePackage不能为空");
        }
        String[] basePackages = null;
        if (basePackage.contains(SPLIT_COMMA)) {
            basePackages = basePackage.split(SPLIT_COMMA);
        } else if (basePackage.contains(SPLIT_SEMICOLON)) {
            basePackages = basePackage.split(SPLIT_SEMICOLON);
        }
        return basePackages;
    }

    public static Predicate<RequestHandler> basePackage(final String[] basePackages) {
        return input -> declaringClass(input).map(handlerPackage(basePackages)).orElse(Boolean.TRUE);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String[] basePackages) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackages) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    @SuppressWarnings("deprecation")
    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.of(input.declaringClass());
    }

}
