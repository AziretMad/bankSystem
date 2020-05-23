/*
package com.company.banksystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)


                .usersByUsernameQuery("select full_name, password, telephone, address, inn, is_active from client where full_name = ?")
                .authoritiesByUsernameQuery("select su.full_name, sur.role_name from client su inner join client_roles sur on sur.client_id = su.id where full_name = ?;");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/client/add").permitAll()
                .antMatchers("/client/all").permitAll()
                .antMatchers( "/client/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/client/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/client/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/bankAccount/add").hasRole("ADMIN")
                .antMatchers("/bankAccount/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/bankAccount/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/bankAccount/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/bankAccount/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/paymentCalculator/**").permitAll()
                .antMatchers(HttpMethod.GET, "/credit/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/credit/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/credit/getById/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/credit/delete/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/credit/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/creditPayment/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/creditPayment/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/creditPayment/add").hasRole("CLIENT")
                .antMatchers(HttpMethod.DELETE, "/creditPayment/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/creditPayment/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/deposit/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/deposit/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/deposit/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/deposit/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/deposit/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/depositAccrual/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/depositAccrual/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/depositAccrual/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/depositAccrual/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/depositAccrual/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/depositAccrual/calculate/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/depositAccrual/calculate/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/exchangeCurrency/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/exchangeCurrency/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/exchangeCurrency/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/exchangeCurrency/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/exchangeCurrency/getByCurrency/{currency}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/exchangeCurrency/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interestRate/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/interestRate/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/interestRate/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interestRate/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interestRate/findByCurrency/{currency}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interestRate/findByDuration/{duration}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interestRate/findByCurrency&Duration{currency}&{duration}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/interestRate/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/property/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/property/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/property/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/property/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/property/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/statementProcess/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/statementProcess/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/statementProcess/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/statementProcess/getAllByStatementType/{type}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/statementProcess/getByAllStatus/{status").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/statementProcess/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/transaction/all").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/transaction/getById/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/transaction/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/transaction/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/transaction/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/transaction/confirmation").hasRole("ADMIN")
                .and().httpBasic().and().csrf().disable();
//                .and().csrf().disable().headers().frameOptions().disable().and()
//
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}*/