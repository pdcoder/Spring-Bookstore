package com.example.demo.config;

import com.example.demo.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import sun.tools.java.Environment;

/**
 * Created by prakashdas on 17/08/18.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserSecurityService userSecurityService;
    private BCryptPasswordEncoder passwordEncoder(){
        return SecurityUtility.passEncoder();
    }

    private static final String[] PUBLIC_MATCHES ={
            "/css/**",
            "/js/**",
            "/image/**",
            "/",
            "/myAccount",
            "newUser",
            "/forgetPassword",
            "/login",
            "/fonts/**",
            "/bookshelf",
            "/bookDetail/**",
            "/hours",
            "/faq",
            "searchByCategory",
            "/searchBook"
    };

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll().anyRequest().authenticated();

        http.csrf().disable().cors().disable().formLogin().failureUrl("/login?error").defaultSuccessUrl("/").loginPage("/login").permitAll().and()
                .logout().logoutRequestMatcher(new AndRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll().and().rememberMe();
    }

    @Autowired
    public  void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}
