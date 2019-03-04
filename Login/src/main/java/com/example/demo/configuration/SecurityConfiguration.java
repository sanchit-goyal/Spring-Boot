package com.example.demo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from user_role where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // for now removing security on h2-console
                .antMatchers("/h2-console/**").hasAuthority("ADMIN")
                // removing login URL from authenticated
                .antMatchers("/login").permitAll()
                // securing all other requests
                .anyRequest().authenticated()
                // defining login page user name/password variables
                .and().formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
                // defining logout URL and redirect URL 
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/login?logout")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().csrf();
    }

}
