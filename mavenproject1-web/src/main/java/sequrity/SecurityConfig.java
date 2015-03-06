/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

/**
 *
 * @author admin
 */

@Configuration
@ComponentScan(value={"sequrity"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired 
    private LoginService userDetailsService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/faces/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/faces/prijava.xhtml").permitAll()
                .failureUrl("/faces/prijava.xhtml?error")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/faces/welcomePrimefaces.xhtml", true)
                .usernameParameter("username").passwordParameter("password")	
            .and()
                .logout().logoutSuccessUrl("/faces/prijava.xhtml");

    }
    
}