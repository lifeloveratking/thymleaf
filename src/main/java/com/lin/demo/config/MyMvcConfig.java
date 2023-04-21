package com.lin.demo.config;


import com.lin.demo.until.MsgUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//如果我们要扩展springmvc，官方建议我们这么做；
@Configuration
 //DelegatingWebMvcConfiguration 导入的这个类，就是为了把所有的webmvcconfig获取
public class MyMvcConfig implements WebMvcConfigurer {
    //viewResolver 实现了视图解析器接口的类，我们就可以把他看作视图解析器；
    //自己定义一个

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
    @Bean
    public MsgUtil localeResolver(){
        return new MsgUtil();
    }
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/login","/","/user/login","/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }

    }

}
