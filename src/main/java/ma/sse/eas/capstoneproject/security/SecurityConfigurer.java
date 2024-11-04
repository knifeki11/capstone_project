package ma.sse.eas.capstoneproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration

public class SecurityConfigurer {


    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/gest_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }



    @Bean
    public JwtInterceptingFilter jwtInterceptingFilter() {
        return new JwtInterceptingFilter();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("rest/users/authenticate").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/rest/users/{tenantId}/users/create").permitAll()
                        .requestMatchers("/rest/users/admin").hasRole("SUPERADMIN")
                        .requestMatchers("/rest/users/**").hasRole("TENANT_ADMIN")
                        .requestMatchers("/rest/suggestions/create").hasAnyRole("USER", "REVIEWER", "UPPER_MANAGER", "TENANT_ADMIN")
                        .requestMatchers("/rest/suggestions/**").hasRole("REVIEWER")
                        .requestMatchers("/rest/reviews/create").hasRole("REVIEWER")
                        .requestMatchers("/rest/reviews/all").hasRole("UPPER_MANAGER")
                        .anyRequest().authenticated()
                )
                .addFilterAfter(jwtInterceptingFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//        http.addFilterBefore(jwtInterceptingFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, active as enabled from app_user where username = ?")
//                .authoritiesByUsernameQuery("select username, authority from authority where username = ?");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("select username, password, 'true' as enabled from \"user\" where username = ?");
        users.setAuthoritiesByUsernameQuery("select username, authority from authority where username = ?");
        return users;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
