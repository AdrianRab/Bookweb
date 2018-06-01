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
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private String usersQuery = "select email, password, enabled from users where email=?";
	private String rolesQuery = "SELECT email, user_role from users INNER JOIN user_roles ON users.user_id=user_roles.user_user_id WHERE email=?";

	// @Bean
	// public UserDetailsService userDetailsServiice() throws Exception {
	// InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	// manager.createUser(User
	// .withUsername("cezar")
	// .password(passwordencoder().encode("Rzym"))
	// .roles("USER").build());
	// manager.createUser(User
	// .withUsername("hannibal")
	// .password(passwordencoder().encode("kanny218"))
	// .roles("USER", "ADMIN").build());
	// manager.createUser(User
	// .withUsername("julek")
	// .password("$2a$10$CLzluRb1aBb1lLlL68VB5udMDX6LAR3SkyBWpH9ze5rwbbmd3.YFy")
	// //hasło Rzym
	// .roles("USER").build());
	// return manager;
	// }

	// @Autowired
	// public void configAuthentication(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/hello", "/publ/**", "/author/**", "/book/**", "/cover/**", "/user/**")
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
