/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package com.maxPotential.MaxxEnergy.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@ComponentScan
public class SpringSecurityConfig {
    public SpringSecurityConfig() {
        super();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        {
            http
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers("/**").permitAll()
                            .requestMatchers("/index").permitAll()
                            .requestMatchers("/public/**").permitAll()
                            .requestMatchers("/favicon.ico").permitAll()
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                            .requestMatchers("/user/**").hasRole("USER")
                            .anyRequest().authenticated())
                    .formLogin((formLogin) ->
                            formLogin
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .loginPage("/login")
                                    .failureUrl("/login-error")
                                    .permitAll()
                    )
                    .logout((logout) -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
                            .deleteCookies("remove")
                            .invalidateHttpSession(true)
                            .permitAll())
                    .exceptionHandling(handling -> handling
                            .accessDeniedPage("/403.html"));
            return http.build();
        }
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("jim").password("{noop}demo").roles("ADMIN").build(),
                User.withUsername("bob").password("{noop}demo").roles("USER").build(),
                User.withUsername("ted").password("{noop}demo").roles("USER", "ADMIN").build());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(User.withUsername("jim").password("{bcrypt}demo").roles("USER").build(),
//                User.withUsername("bob").password("{bcrypt}demo").roles("USER","EMPLOYEE").build(),
//                User.withUsername("ted").password("{bcrypt}demo").roles("USER","EMPLOYEE","ADMIN").build()
//        );
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
//    // Create AuthenticationManagerBuilder with AuthenticationConfiguration
//    AuthenticationManagerBuilder authenticationManagerBuilder =
//            new AuthenticationManagerBuilder(authConig);
//
//    authenticationManagerBuilder
//            .inMemoryAuthentication()
//            .withUser("user").password(passwordEncoder().encode("password")).roles("USER", "EMPLOYEE")
//            .and()
//            .withUser("gladmin").password(passwordEncoder().encode("emp")).roles("USER", "EMPLOYEE")
//            .and()
//            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "EMPLOYEE", "USER");
//
//    return authenticationManagerBuilder.build();
//    }
}

//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager builder = new InMemoryUserDetailsManager();
//        builder().withUser("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .and()
//                .withUser("Emploe")
//                .password(passwordEncoder().encode("emploe"))
//                .roles("USER", "EMPLOYEE");
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("USER", "EMPLOYEE","ADMIN");
//        return builder.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

