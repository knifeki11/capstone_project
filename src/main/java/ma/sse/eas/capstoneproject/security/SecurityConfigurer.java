package ma.sse.eas.capstoneproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigurer {

    private final DataSource dataSource;

    public SecurityConfigurer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JwtInterceptingFilter jwtInterceptingFilter() {
        return new JwtInterceptingFilter();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/rest/user/authenticate").permitAll()
                        .requestMatchers("/rest/user/admin").hasRole("SUPERADMIN")
                        .requestMatchers("/rest/tenant/**").hasRole("TENANT_ADMIN")
                        .requestMatchers("/rest/suggestions/create").hasAnyRole("USER", "REVIEWER", "UPPER_MANAGER", "TENANT_ADMIN")
                        .requestMatchers("/rest/suggestions/**").hasRole("REVIEWER")
                        .requestMatchers("/rest/reviews/create").hasRole("REVIEWER")
                        .requestMatchers("/rest/reviews/all").hasRole("UPPER_MANAGER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtInterceptingFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, active as enabled from \"user\" where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authority where username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
