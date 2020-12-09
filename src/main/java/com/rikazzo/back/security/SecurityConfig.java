package com.rikazzo.back.security;

import com.rikazzo.back.security.jwt.JwtFilterRequest;
import com.rikazzo.back.service.UsuarioService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsuarioService usuarioService;
    private final JwtFilterRequest jwtFilterRequest;

    @Autowired
    public SecurityConfig(UsuarioService usuarioService, JwtFilterRequest jwtFilterRequest) {
        this.usuarioService = usuarioService;
        this.jwtFilterRequest = jwtFilterRequest;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                /*------------------------------- ENDPOINTS PARA QUE CUALQUIERA -------------------------------*/
                .antMatchers(HttpMethod.POST,"/usuarios", "/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/autores", "/autores/nombre/{nombre}", "/libros", "/libros/nombre/{nombre}", "/libros/estado/{estado}", "/swagger-ui.html",
                        "/webjars/**", "/swagger-resources/**", "/v2/api-docs/**").permitAll()
                /*----------------------------------------------------------------------------------------------------------------------------*/
                /*-------------------------------  -------------------------------*/
                .antMatchers(HttpMethod.GET,"/alquileres/usuario/{username}").hasAnyRole("USER", "BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/usuarios").hasAnyRole("USER", "BIBLIOTECARIO", "ADMIN")

                .antMatchers(HttpMethod.GET, "/usuarios").hasAnyRole("BIBLIOTECARIO", "ADMIN")

                //--------------------------- CRU CATEGORIA ---------------------------
                .antMatchers(HttpMethod.GET, "/usuarios/{idUsuario}").hasAnyRole("USER", "BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/categorias").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/categorias").hasRole("ADMIN")

                //--------------------------- CRU CATEGORIA ---------------------------
                .antMatchers(HttpMethod.GET, "/categorias").hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.GET, "/categorias/{idCategoria}").hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/categorias").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/categorias").hasRole("ADMIN")

                //--------------------------- CRU CATEGORIA ---------------------------
                .antMatchers(HttpMethod.GET, "/idiomas").hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.GET, "/idiomas/{idIdioma}").hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/idiomas").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/idiomas").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/idiomas/{idIdioma}").hasRole("ADMIN")

                //--------------------------- CRU AUTOR ---------------------------
                .antMatchers(HttpMethod.GET, "/autores/{idAutor}").hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/autores").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/autores").hasRole("ADMIN")

                //--------------------------- CRU EDITORIAL ---------------------------
                .antMatchers(HttpMethod.GET, "/editoriales").hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/editoriales").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/editoriales").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/editoriales").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/editoriales/eliminar/{idEditorial}").hasAnyRole("ADMIN")

                //--------------------------- CRUD LIBRO ---------------------------
                .antMatchers(HttpMethod.POST, "/libros").hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/libros").hasAnyRole("BIBLIOTECARIO", "ADMIN")

                //------------------------------
                .antMatchers(HttpMethod.GET, "/alquileres").hasAnyRole("BIBLIOTECARIO", "ADMIN")

                /*-------------------------------  -------------------------------*/
                .antMatchers(HttpMethod.DELETE, "/libros/{idLibro}", "/alquileres/eliminar/{idAlquiler}",
                        "/autores/{idAutor}", "/editoriales/eliminar/{idEditorial}",
                        "/categorias/{idCategoria}", "/usuarios/{idUsuario}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(this.jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> filterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }



}
