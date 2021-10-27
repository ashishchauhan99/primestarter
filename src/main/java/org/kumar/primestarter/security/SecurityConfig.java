package org.kumar.primestarter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private LoginSucessHandler securityHandler;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, LoginSucessHandler securityHandler) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.securityHandler = securityHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/javax.faces.resource/**").permitAll()
                .antMatchers("/registration.xhtml").permitAll().anyRequest().authenticated().and().formLogin()
                .loginPage("/login.xhtml").permitAll().successHandler(securityHandler)
                .failureUrl("/login.xhtml?error=true").and().logout().logoutSuccessUrl("/login.xhtml")
                .deleteCookies("JSESSIONID");
//        defaultSuccessUrl("/dashboard.xhtml", true)

    }

    /**
     * In Memory and Database authentiation do not work together. Either you need to provide AuthenticationProvider or
     * use the auth.inMemoryAuthentication() (in commented out below method)
     *
     * @return
     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("ashish").password("{noop}pw").roles("USER").and().withUser("kumar")
//                .password("{noop}pw").roles("ADMIN");
//
//    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsServiceImpl);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}