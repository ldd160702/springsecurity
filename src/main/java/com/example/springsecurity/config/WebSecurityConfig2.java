//package com.example.springsecurity.config;
//
//import com.example.springsecurity.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService) // Cung cáp userservice cho spring security
//                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/logout").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/add-user").hasRole("ADMIN")
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/", "/home").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
//                .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
//                .and()
//                .formLogin() // Cho phép người dùng xác thực bằng form login
//                .loginPage("/login")
//                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/hello")
//                .and()
//                .logout() // Cho phép logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/home")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID");
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
