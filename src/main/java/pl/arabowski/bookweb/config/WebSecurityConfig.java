package pl.arabowski.bookweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	 @Bean(name="passwordEncoder")
	    public PasswordEncoder passwordencoder(){
	     return new BCryptPasswordEncoder();
	 }
	 	@Autowired
		private DataSource dataSource;
	 
//	 @Autowired 
//	 private UserDetailsService userDetailsService;
	 	
	 	@Bean
	 	public UserDetailsService userDetailsServiice() throws Exception {
	 		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 		manager.createUser(User
	 				.withUsername("cezar")
	 				.password(passwordencoder().encode("Rzym"))
	 				.roles("USER").build());
	 		manager.createUser(User
	 				.withUsername("hannibal")
	 				.password(passwordencoder().encode("kanny218"))
	 				.roles("USER", "ADMIN").build());
			manager.createUser(User
	 				.withUsername("julek")
	 				.password("$2a$10$CLzluRb1aBb1lLlL68VB5udMDX6LAR3SkyBWpH9ze5rwbbmd3.YFy") //hasło Rzym
	 				.roles("USER").build());
	 		return manager;
	 	}
	 
//	 @Autowired
//	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
//		 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
//	 } 
	 
//		@Autowired
//		public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {	
//			auth.jdbcAuthentication().dataSource(dataSource)
//	        .usersByUsernameQuery("select username, password, enabled"
//	            + " from users where username=?")
//	        .authoritiesByUsernameQuery("select username, role "
//	            + "from user_roles where username=?")
//	        .passwordEncoder(new BCryptPasswordEncoder());
//
//		}
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests()
	  .antMatchers("/hello", "/publ/**", "/author/**", "/book/**", "/cover/**").access("hasRole('ROLE_USER')")
	  .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
	  .anyRequest().permitAll()
	  .and()
	    .formLogin()
	    .usernameParameter("username").passwordParameter("password")
	  .and()
	    .logout().logoutSuccessUrl("/home") 
	  .and()
	    .csrf().disable();
	 }
}
