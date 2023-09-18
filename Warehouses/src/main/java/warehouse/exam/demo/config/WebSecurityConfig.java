package warehouse.exam.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService jwtUserDetailsService;

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

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> {
//                    Map<String, Object> responseMap = new HashMap<>();
//                    ObjectMapper mapper = new ObjectMapper();
//                    response.setStatus(401);
//                    responseMap.put("error", true);
//                    responseMap.put("message", "Unauthorized");
//                    response.setHeader("content-type", "application/json");
//                    String responseMsg = mapper.writeValueAsString(responseMap);
//                    response.getWriter().write(responseMsg);
//                })
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                // Quyền truy cập cho các trang MVC
                .antMatchers("/auth/login", "/auth/logout").permitAll()
                .antMatchers("/", "/auth/index","auth/search" , "/qc/index", "/itemaster/index", "/import/index",
                        "/itemdata/index", "/location/index", "/warehouse/index").hasAnyAuthority("ROLE_1", "ROLE_2", "ROLE_3", "ROLE_4")
                .antMatchers("/auth/**", "/import/**", "/itemaster/**", "/warehouse/**", "/itemdata/**", "/location/**").hasAuthority("ROLE_1")
                .antMatchers("/import/**").hasAuthority("ROLE_2")
                .antMatchers("/qc/**").hasAuthority("ROLE_3")
                .antMatchers("/itemdata/**").hasAuthority("ROLE_4")
                .anyRequest().permitAll()  // Cho phép truy cập không cần xác thực cho các request khác

                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    ObjectMapper mapper = new ObjectMapper();
                    response.setStatus(401);
                    responseMap.put("error", true);
                    responseMap.put("message", "Unauthorized");
                    response.setHeader("content-type", "application/json");
                    String responseMsg = mapper.writeValueAsString(responseMap);
                    response.getWriter().write(responseMsg);
                })

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()

                .and()
                .logout()
                .permitAll();


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
