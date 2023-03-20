package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeRequests()
				.antMatchers("/taco", "orders").access("hasRole('USER')")
				.antMatchers("/","/**").access("permitAll()")
			.and() 
				.oauth2Login()
					//.loginPage("/login") // To offer both
			.and()
				.logout()
					.logoutSuccessUrl("/")
			.and()
			.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService(UserRepository userRepo) {
//		return username -> {
//			User user = userRepo.findByUsername(username);
//			if (user != null)
//				return user;
//			throw new UsernameNotFoundException("User '" + username + "' not found");
//		};
//	}
}
