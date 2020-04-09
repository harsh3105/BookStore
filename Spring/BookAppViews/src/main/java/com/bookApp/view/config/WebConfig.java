package com.bookApp.view.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.activation.DataSource;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.bookApp.view.service.UserDetailsServiceImp;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@EnableWebSecurity
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
    public WebConfig(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }
	
	@Bean
	  public UserDetailsService userDetailsService() {
	    return new UserDetailsServiceImp();
	  };
	  
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	
	 @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//            .withUser("blah")
//            .password("blah")
//            .roles("USER")
//            .and()
//            .withUser("foo")
//            .password("foo")
//            .roles("ADMIN");
            
            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasAnyRole("USER")
		.antMatchers("/").permitAll()
		.and().formLogin().loginPage("/login")
		.successHandler(successHandler).permitAll(true);
		http.csrf().disable();
	}
	
	
}
