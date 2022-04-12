package pl.kosinski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import pl.kosinski.charity.UserLoginFilter;

@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<UserLoginFilter> loginFilterRegistration(){
        FilterRegistrationBean<UserLoginFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new UserLoginFilter());
        registrationBean.addUrlPatterns("/institution/*");
        registrationBean.addUrlPatterns("/user/profile/*");
        registrationBean.addUrlPatterns("/user/list/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }

}
