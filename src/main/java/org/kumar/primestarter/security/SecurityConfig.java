package org.kumar.primestarter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login.xhtml").permitAll()
                .failureUrl("/login.xhtml?error=true").defaultSuccessUrl("/dashboard.xhtml", true).and().logout()
                .logoutSuccessUrl("/login.xhtml").deleteCookies("JSESSIONID");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ashish").password("{noop}pw").roles("USER").and().withUser("kumar")
                .password("{noop}pw").roles("ADMIN");

    }
}