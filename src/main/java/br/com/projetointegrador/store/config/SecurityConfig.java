package br.com.projetointegrador.store.config;

import br.com.projetointegrador.store.enums.UserRole;
import br.com.projetointegrador.store.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.formLogin(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults()); // by default uses a Bean by the name of corsConfigurationSource
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                .requestMatchers(HttpMethod.GET, "/auth/teste").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users/getById/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/getImage/{id}/{image}").permitAll()
                .requestMatchers(HttpMethod.POST, "/client/register").permitAll()
                .requestMatchers(HttpMethod.PUT, "/client/update/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/order/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/order/myOrders/{uuid}").permitAll()
                .requestMatchers(HttpMethod.GET, "/order/orderCode/{orderCode}").permitAll()
                .requestMatchers(HttpMethod.GET, "/endereco/myAddress/{uuid}").permitAll()
                .requestMatchers(HttpMethod.GET, "/endereco/defaultAddress/{uuid}").permitAll()
                .requestMatchers(HttpMethod.GET, "/info/statusOrder").permitAll()
                .requestMatchers(HttpMethod.GET, "/info/shippings").permitAll()
                .requestMatchers(HttpMethod.GET, "/info/paymentsMethods").permitAll()
                .requestMatchers(HttpMethod.GET, "/info/roles").permitAll()
                .requestMatchers(HttpMethod.GET, "/info/genders").permitAll()
                .requestMatchers(HttpMethod.PUT, "/endereco/newDeliveryAddress/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/endereco/newDefaultAddress/{id}/{idAddress}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/endereco/changeDeliveryAddressStatus/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/endereco/myAddress/{uuid}").permitAll()
                .requestMatchers(HttpMethod.GET, "/endereco/defaultAddress/{uuid}").permitAll()
                .requestMatchers(HttpMethod.GET, "/products", "/products/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/listingAllProducts").permitAll()
                .requestMatchers(HttpMethod.GET, "/products/listingProduct/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/products", "/editProduct/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/home/listingProducts").permitAll()
                .requestMatchers(HttpMethod.GET, "/products", "/products/getImage/{id}/{image}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasAnyRole(UserRole.ADMIN.name())
                .anyRequest().authenticated());

        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3030"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}