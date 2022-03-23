package pl.kosinski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kosinski.charity.LoginFilter;

import java.util.logging.Filter;

//@Configuration
@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilterRegistration(){
        FilterRegistrationBean<LoginFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/institution/*");
        registrationBean.addUrlPatterns("/user/profile/*");
        registrationBean.addUrlPatterns("/user/list/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }

//    public LoginFilter loginFilter () {
//        return new LoginFilter();
//    }

}
