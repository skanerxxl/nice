package ru.skanerxxl.rambler.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.skanerxxl.rambler.database.entity.Administrator;
import ru.skanerxxl.rambler.service.ServiceSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan("ru.skanerxxl.rambler")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ServiceSecurity serviceSecurity;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        Administrator administrator = new Administrator();
        auth.inMemoryAuthentication()
                .withUser(administrator.getLogin())
                .password(administrator.getPassword()).
                roles(administrator.getRoles());
        auth.userDetailsService(serviceSecurity)
                .passwordEncoder(new ShaPasswordEncoder());
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", false)
                .failureUrl("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}
