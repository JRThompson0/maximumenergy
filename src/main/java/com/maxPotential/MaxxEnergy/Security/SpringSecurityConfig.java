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


import com.maxPotential.MaxxEnergy.Web.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@ComponentScan
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private PasswordEncoder passwordEncoder;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        {
            http
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                            .requestMatchers("/user/**").hasRole("USER")
                            .requestMatchers("/**").permitAll()
                            .requestMatchers("/index").permitAll()
                            .requestMatchers("/public/**").permitAll()
                            .requestMatchers("/registered").permitAll()
                            .requestMatchers("/favicon.ico").permitAll()
                            .anyRequest().authenticated())
                    .formLogin((formLogin) ->
                            formLogin
                                    .usernameParameter("username")
                                    .passwordParameter("password")
                                    .loginPage("/login")
                                    .defaultSuccessUrl("/home", true) // Redirect after successful login
                                    .failureUrl("/login?error=true")
                                    .permitAll()
                    )
                    .logout((logout) -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
                            .deleteCookies("remove")
                            .invalidateHttpSession(true)
                            .permitAll())
                    .exceptionHandling(handling -> handling
                            .accessDeniedPage("/403"));
            return http.build();
        }
    }

//        @Bean
//        public InMemoryUserDetailsManager userDetailsServiceTest() {
//            return new InMemoryUserDetailsManager(
//                    User.withUsername("jim").password("{noop}demo").roles("ADMIN").build(),
//                    User.withUsername("bob").password("{noop}demo").roles("USER").build(),
//                    User.withUsername("ted").password("{noop}demo").roles("USER", "ADMIN").build());
//        }
}

