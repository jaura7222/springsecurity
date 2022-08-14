package org.hdcd.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.hdcd.common.security.handler.CustomLoginFailureHandler;
import org.hdcd.common.security.handler.CustomLoginSuccessHandler;
import org.hdcd.common.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	DataSource dataSource;
	
	
	/*
	 * @Autowired private CustomAuthenticationProvider cuap;
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * cuap.setUserDetailsService((CustomUserDetailsService)customUserDetailsService
		 * ()); auth.authenticationProvider(cuap);
		 */
		
		auth.userDetailsService(customUserDetailsService())
		.passwordEncoder(passwordEncoder());
	}
	
	
	/*
	 * 
	//userDetails Customizing 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService());
		//.passwordEncoder(passwordEncoder());
	}
	*/
	
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String query1 = "select user_id, user_pw, enabled FROM member where user_id = ?";
		String query2 = "select b.user_id, a.auth FROM member_auth a, member b where a.user_no = b.user_no and b.user_id = ? ";
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(query1)
		.authoritiesByUsernameQuery(query2);
		//.passwordEncoder(passwordEncoder());
	}
	*/
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource);
		//.passwordEncoder(PasswordEncoder());
	}
	*/
	
	/*
	 * @Autowired public void initialize(AuthenticationManagerBuilder auth,
	 * DataSource dataSource) throws Exception { auth.jdbcAuthentication()
	 * .dataSource(dataSource) .passwordEncoder(passwordEncoder()); }
	 */
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("alex").password("1234").roles("MEMBER")
		.and()
		.withUser("jade").password("1234").roles("MEMBER","ADMIN")
		.and()
		.withUser("admin").password("1234").roles("ADMIN");
	}
	*/
	
	/*
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		UserDetails alex = users.username("alex").password("1234").roles("MEMBER").build();
		UserDetails jade = users.username("jade").password("1234").roles("MEMBER", "ADMIN").build();
		UserDetails admin = users.username("admin").password("1234").roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(alex, jade, admin);
				
	}
	*/
	


	@SuppressWarnings("deprecation")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
		.loginPage("/auth/login")
		.usernameParameter("uid")
		.passwordParameter("pwd")
		//.loginProcessingUrl("/signin")
		.failureUrl("/auth/login?error")
		.defaultSuccessUrl("/")
		.successHandler(authenticationSuccessHandler())
		.failureHandler(authenticationFailureHandler());
		
		http.authorizeRequests()
		//.antMatchers("/css/**", "/js/**").permitAll()
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()	//정적 리소스 위치를 알려주는 메서드 사용
		.antMatchers("/").permitAll()
		.antMatchers("/auth/login").permitAll()
		.antMatchers("/error/**").permitAll()
		.antMatchers("/user/register", "/user/registerSuccess" ).permitAll()
		.antMatchers("/codegroup/**").hasRole("ADMIN")
		.antMatchers("/codedetail/**").hasRole("ADMIN")
		.antMatchers("/board/list","/board/read").permitAll()
		.antMatchers("/board/register", "/board/modify").hasRole("MEMBER")
		.antMatchers("/board/remove").hasAnyRole("MEMBER", "ADMIN")
		.antMatchers("/notice/list","/notice/read").permitAll()
		.antMatchers("/notice/register","/notice/modify", "/notice/remove").hasRole("ADMIN")
		.anyRequest().authenticated();
		
		http.logout()
		//.logoutUrl("/auth/logout")
		.logoutSuccessUrl("/")
		.permitAll();
		
		
		http.rememberMe()
		.key("asdfasdfasdf")
		.tokenValiditySeconds(60*60*24);
		
		
		//접근 거부 에러페이지
		http.exceptionHandling()
		.accessDeniedPage("/error/accessError");
		
		
		/*
		 * http.addFilterAt(new
		 * CustomUsernamePasswordAuthenticationFilter(authenticationManager()),
		 * UsernamePasswordAuthenticationFilter.class);
		 */
		
		
		/*
		http.httpBasic();
		
		http.authorizeRequests()
		.anyRequest().authenticated();
		*/
		//폼 인증 활성화
		/*
		http.formLogin()
		.and()
		.authorizeRequests()
		.anyRequest().authenticated();
		*/
	}


    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler();
    }


	
	
@Bean 
PasswordEncoder passwordEncoder() { 
	return new BCryptPasswordEncoder();
 }
	
	
    
    @Bean
    public UserDetailsService customUserDetailsService() {
	    return new CustomUserDetailsService();
    }
    
    
    

}
