package com.rental.configuration;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.rental.auth.JwtTokenFilter;
import com.rental.auth.JwtTokenProvider;
import com.rental.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    @Value("${jwt-secret-key}")
    private String jwtKey ;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.userDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/register").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public Filter jwtTokenFilter() {
        return new JwtTokenFilter(userDetailsService, jwtTokenProvider);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    }

    // Token Bearer Decoder
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(
                this.jwtKey.getBytes(StandardCharsets.UTF_8),
                "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    // Token Bearer Encoder
    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes(StandardCharsets.UTF_8)));
    }

    // BCryptPasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
