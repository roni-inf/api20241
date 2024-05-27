package br.com.serratec.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.serratec.security.JwtAuthenticationFilter;
import br.com.serratec.security.JwtAuthorizationFilter;
import br.com.serratec.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception {
	 * 
	 * http.csrf(csrf -> csrf.disable()).cors(cors ->
	 * cors.configurationSource(corsConfigurationSource()).disable())
	 * .httpBasic(Customizer.withDefaults()).authorizeHttpRequests(requests -> {
	 * requests.requestMatchers(HttpMethod.GET, "/usuarios").permitAll();
	 * requests.requestMatchers(new
	 * AntPathRequestMatcher("/h2-console/**")).permitAll();
	 * requests.requestMatchers(HttpMethod.GET,
	 * "/funcionarios").hasRole("RH").anyRequest() .authenticated();
	 * }).sessionManagement(session ->
	 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	 * .headers((headers) -> headers.disable()); return http.build(); }
	 */

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors((cors) -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(requests -> requests.requestMatchers("/public/**").permitAll()
						.requestMatchers("/funcionarios").permitAll()
						.requestMatchers("/funcionarios/salarios-por-idade").permitAll()
						.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
						.requestMatchers(HttpMethod.GET, "//funcionarios/salario", "/funcionarios/pagina",
								"/funcionarios/nome")
						.hasRole("ADMIN").requestMatchers(HttpMethod.GET, "/usuarios")
						.hasAnyRole("ADMIN", "USER").requestMatchers(HttpMethod.POST, "/usuarios")
						.hasRole("ADMIN").anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers((headers) -> headers.disable());

		http.addFilter(new JwtAuthenticationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil,
				userDetailsService));
		return http.build();
	}

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http.csrf(csrf -> csrf.disable()).cors((cors) ->
	 * cors.configurationSource(corsConfigurationSource()))
	 * .httpBasic(Customizer.withDefaults()).authorizeHttpRequests(requests -> {
	 * requests.requestMatchers(HttpMethod.GET, "/funcionarios").permitAll();
	 * requests.requestMatchers(HttpMethod.POST, "/funcionarios/salario",
	 * "/funcionarios/pagina", "/funcionarios/buscarPorNome").hasAuthority("ADMIN");
	 * requests.requestMatchers(HttpMethod.GET,
	 * "/usuarios").hasRole("ADMIN").anyRequest().authenticated();
	 * 
	 * }).sessionManagement(session ->
	 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	 * 
	 * http.addFilter(new JwtAuthenticationFilter(
	 * authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)
	 * ), jwtUtil)); http.addFilter(new JwtAuthorizationFilter(
	 * authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)
	 * ), jwtUtil,userDetailsService));
	 * 
	 * 
	 * return http.build(); }
	 */

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000/", "http://localhost:2000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
	}

}
