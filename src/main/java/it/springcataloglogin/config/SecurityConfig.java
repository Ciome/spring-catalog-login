package it.springcataloglogin.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import it.springcataloglogin.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Autowired
    private UserService userService;
	
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    
	@Autowired
	@Qualifier("securityDataSource")
	private DataSource dataSource;

	@Bean
	public UserDetailsManager userDetailManager() {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http
				.authorizeRequests(configurer -> configurer
						.antMatchers("/").permitAll()
						.antMatchers("/admin/**").hasRole("ADMIN")
						.antMatchers("/account/**").authenticated()
						.antMatchers("/catalog/showFormForAdd").authenticated()
						.antMatchers("/catalog/showFormForUpdate").authenticated()
						.antMatchers("/catalog/save").authenticated()
						.antMatchers("/catalog/delete").authenticated())
				.formLogin(configurer -> configurer
						.loginPage("/login")
						.loginProcessingUrl("/authenticateTheUser")
						.successHandler(authenticationSuccessHandler)
						.permitAll())
				.logout(configurer -> configurer.permitAll())
				.exceptionHandling(configurer -> configurer.accessDeniedPage("/accessDenied"))
				.build();
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(encoder);
		return auth;
	}
	
}
