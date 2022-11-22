package pl.arabowski.bookweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource dataSource;

	private String usersQuery = "select email, password, enabled from users where email=?";
	private String rolesQuery = "SELECT email, user_role from users INNER JOIN user_roles ON users.user_id=user_roles.user_user_id WHERE email=?";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/hello", "/publisher/**", "/author/**", "/book/**", "/cover/**", "/user/**")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
				.antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutSuccessUrl("/home")
				.and()
				.csrf().disable(); // bez
																												// disable
																												// 403
																												// przy
																												// dodwaniu
																												// image
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
