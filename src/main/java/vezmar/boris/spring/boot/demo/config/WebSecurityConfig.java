package vezmar.boris.spring.boot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers(HttpMethod.GET, "/jokes").permitAll()
				.antMatchers(HttpMethod.GET, "/jokes/count").permitAll()
				.antMatchers(HttpMethod.POST, "/jokes/{\\d+}/like").permitAll()
				.antMatchers(HttpMethod.POST, "/jokes/{\\d+}/dislike").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("pperic").password(passwordEncoder().encode("pero")).roles("USER").and()
				.withUser("iivic").password(passwordEncoder().encode("ivan")).roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
