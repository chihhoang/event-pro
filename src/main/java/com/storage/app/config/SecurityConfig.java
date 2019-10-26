package com.storage.app.config;

import com.storage.app.security.JwtConfigure;
import com.storage.app.security.JwtTokenProvider;
import javax.annotation.Resource;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Resource private JwtTokenProvider tokenProvider;
  @Resource private CustomUserDetailsService customUserDetailsService;

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsFilter corsFilter() {
    return new CorsFilter();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests()
        .antMatchers("/api/authenticate")
        .permitAll()
        .antMatchers("/users/signup")
        .permitAll()
        .anyRequest()
        .authenticated();
    http.exceptionHandling().accessDeniedPage("/login");

    http.apply(new JwtConfigure(tokenProvider));
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .antMatchers("/h2-console/**")
        .antMatchers("/v2/api-docs")
        .antMatchers("/configuration/**")
        .antMatchers("/webjars/**")
        .antMatchers("/public")
        .antMatchers("/swagger-resources/**")
        .antMatchers("/swagger-ui.html")
        .antMatchers("/test/**");
  }
}
