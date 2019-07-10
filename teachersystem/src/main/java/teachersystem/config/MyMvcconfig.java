package teachersystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import teachersystem.component.LoginHandlerInterceptor;
import teachersystem.component.MyLocaleResolver;

@Configuration
//既保留了SpringMvc所有的自动配置，也能用我们扩展的配置；
public class MyMvcconfig extends WebMvcConfigurerAdapter {

    @Bean
    //一定加入容器，将组建注册容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override

            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/menu.html").setViewName("menu");
                //  registry.addViewController("/addTeacher.html").setViewName("add");
                registry.addViewController("/update.html").setViewName("update");
                registry.addViewController("/delete.html").setViewName("addViewControllers");
                registry.addViewController("/list.html").setViewName("list");
                registry.addViewController("/add.html").setViewName("add");

                registry.addViewController("/").setViewName("login");
                registry.addViewController("/trace.html").setViewName("trace");
                registry.addViewController("/error.html").setViewName("error");


            }

            //拦截器注册
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/teacher/login");
            }
        };


        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
