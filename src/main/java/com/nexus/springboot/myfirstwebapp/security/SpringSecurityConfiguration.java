package com.nexus.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetails1 = createNewUser("Pablo", "4dm1N");
        UserDetails userDetails2 = createNewUser("Admin", "Admin");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails createNewUser(String username, String password) {
        //Lambda function that has a String as input and output
        Function<String, String> passwordEncoder
                = input -> passwordEncoder().encode(input);

        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    /***
     * Disables Cross-Site Request Forgery to access to H2 console
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //SecurityFilterChain: Defines a filter chain matched against every request

        //All the requests are authenticated
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());

        //If there is an unauthorized request, the login form is shown
        httpSecurity.formLogin(withDefaults());

        //When SecurityFilterChain is overridden, the entire chain has to be defined again
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable(); //Enabling use of frames on the application

        return httpSecurity.build();
    }

}
