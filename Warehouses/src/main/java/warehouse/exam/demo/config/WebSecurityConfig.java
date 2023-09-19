package warehouse.exam.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService jwtUserDetailsService;
    @Autowired
    private SessionAuthenticationFilter sessionAuthenticationFilter;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    public WebSecurityConfig(UserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(sessionAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                // Quyền truy cập cho các trang MVC
                .antMatchers("/auth/login", "/auth/logout",  "/auth/index").permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint());


        //        httpSecurity
//                .exceptionHandling()
//                .accessDeniedPage("/error/error")
//                .and()
//                .authorizeRequests();

        //Permission for mvc
//                .antMatchers("/auth/index", "/qc/index", "/itemaster/index", "/import/index",
//                        "/itemdata/index", "/location/index", "/warehouse/index").hasAnyAuthority("1", "2", "3", "4")
//                .antMatchers("/auth/**", "/import/**", "/itemaster/**",
//                        "/warehouse/**", "/itemdata/**", "/location/**").hasAuthority("1")
//                .antMatchers("/import/**").hasAuthority("2")
//                .antMatchers("/qc/**").hasAuthority("3")
//                .antMatchers("/itemdata/**").hasAuthority("4");
    }
}
