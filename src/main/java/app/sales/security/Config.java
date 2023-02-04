package app.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceDetails userService;
    @Autowired
    private JwtUtils jwtService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean/*Implementação da autenticação por JWT*/
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService,userService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/users/findAll").permitAll()
                .antMatchers("/api/products/findAll").permitAll()
                .antMatchers("/api/projects/findAll").permitAll()
                .antMatchers("/api/gerenciamento/imagens/load_data").permitAll()
                .antMatchers("/api/project**").permitAll()
                .and()
                .httpBasic();
        http
                .csrf().disable().authorizeRequests()
                .antMatchers("/api/clientes/**").hasRole("ADMIN")
                .antMatchers("/api/produtos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/usuarios").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
