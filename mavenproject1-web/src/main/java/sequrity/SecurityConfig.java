/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sequrity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
                        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
                        if(a.getAuthorities().contains(new SimpleGrantedAuthority(LoginService.ROLE_PROFESOR))){
                            redirectStrategy.sendRedirect(hsr, hsr1, "/faces/schedule_profesor.xhtml");
                        } else {
                            redirectStrategy.sendRedirect(hsr, hsr1, "/faces/schedule.xhtml");
                        }
                    }
                })
//                .defaultSuccessUrl("/faces/schedule.xhtml", true)
                .usernameParameter("username").passwordParameter("password")	
            .and()
                .logout().logoutSuccessUrl("/faces/prijava.xhtml");

    }
    
}