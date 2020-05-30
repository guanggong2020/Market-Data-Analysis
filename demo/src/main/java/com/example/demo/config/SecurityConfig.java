package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService getServiceDetail() {
        return new UserService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 定义开放资源，自定义界面，“记住我”功能...
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 默认是所有请求都加上cache-control:no-cache
        http.headers().cacheControl().disable();

        http.
                authorizeRequests().antMatchers("/", "/register", "/gupiao_data/**", "/jijin_data/**", "/USA_stock_data/**", "/shangzheng_shenzheng_data/**", "/USA_fund_data/**", "/job/**", "/jobs/**", "/druid/**")
                .permitAll()
                .antMatchers("/administrators/**", "/users/**", "/administrator/**", "/user/**").hasRole("ADMIN")
                .antMatchers("/gpjjlist/**", "/jobs/**", "/search/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/doLogin").successForwardUrl("/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().
                logout().logoutSuccessUrl("/")
                .and().
                rememberMe().rememberMeParameter("remember")
                .and()
                // 关闭csrf功能，不然会拦截delete请求
                .csrf().disable();
    }

    // 定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("1234"))
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password(new BCryptPasswordEncoder().encode("1234"))
//                .roles("USER");

        auth.userDetailsService(getServiceDetail())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/assert/**");
    }
}
