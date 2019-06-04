package org.superbiz.struts;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StrutsSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrutsSpringApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean loadFilterRegistrationBean() {
        return createFilterRegistrationBean(new StrutsPrepareAndExecuteFilter(), populateUrlPatterns(), 2);
    }

    @Bean
    public FilterRegistrationBean sitemeshFilterRegistrationBean() {
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        return createFilterRegistrationBean(new SiteMeshFilter(), urlPatterns, 1);
    }

    private FilterRegistrationBean createFilterRegistrationBean(Filter filter, List<String> urlPatterns, int order) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        filterRegistrationBean.setOrder(order);
        return filterRegistrationBean;
    }

    private List<String> populateUrlPatterns() {
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/");
        urlPatterns.add("/addUserForm.action");
        urlPatterns.add("/addUser.action");
        urlPatterns.add("/findUserForm.action");
        urlPatterns.add("/findUser.action");
        urlPatterns.add("/listAllUsers.action");
        return urlPatterns;
    }
}
