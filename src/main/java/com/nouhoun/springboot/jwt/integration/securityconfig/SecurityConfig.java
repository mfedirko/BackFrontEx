package com.nouhoun.springboot.jwt.integration.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Created by mifedirko on 02/24/2018.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Value("${security.signing-key}")
//	private String signingKey;
//
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;
//
//	@Value("${security.security-realm}")
//	private String securityRealm;
//
//	@Autowired
//	private UserDetailsService userDetailsService;


//	@Override
//	public void configure(WebSecurity security)
//	{
//	    security.ignoring().antMatchers("/welcome.jsp");
//	}
	
	@Configuration
	@Order(1)
	public static class NonFormSecurityConfig extends WebSecurityConfigurerAdapter{
		@Value("${security.signing-key}")
		private String signingKey;

		@Value("${security.encoding-strength}")
		private Integer encodingStrength;

		@Value("${security.security-realm}")
		private String securityRealm;

		@Autowired
		private UserDetailsService userDetailsService;
		
		
	
		@Bean
		@Override
		protected AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService)
			        .passwordEncoder(new ShaPasswordEncoder(encodingStrength));
		}
		
	//	 

		 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		  .authorizeRequests()
	      .antMatchers("/login*","/welcome*","/logout*","/home*")
	      .permitAll()
	      .antMatchers("/admin/**")
	      .hasAnyAuthority("ADMIN_USER")
	      .antMatchers("/**")
	      .hasAnyAuthority("STANDARD_USER","ADMIN_USER")
	      .and()
	      .formLogin()
	      .defaultSuccessUrl("/home.jsp")
	      .and().csrf()
	      .disable()
	      ;

	    http.logout()
	        .clearAuthentication(true)
	        .invalidateHttpSession(true);
//				.authorizeRequests()
//				.antMatchers("/transaction/**","/springjwt/**")
//				.authenticated()
//				.antMatchers("/welcome.jsp")
//				.authenticated()
//				.and()
//				.formLogin()
//				.successHandler(new RefererRedirectionAuthenticationSuccessHandler())
//				.and()
//				.httpBasic()
//				.realmName(securityRealm)
//				.and()
//		        .sessionManagement()
//		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		        .and()
//		        .csrf()
//		        .disable();

	}

//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey(signingKey);
//		return converter;
//	}
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
//	}
//
//	@Bean
//	@Primary //Making this primary to avoid any accidental duplication with another token service instance of the same name
//	public DefaultTokenServices tokenServices() {
//		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//		defaultTokenServices.setTokenStore(tokenStore());
//		defaultTokenServices.setSupportRefreshToken(true);
//		return defaultTokenServices;
//	}
	
	}
	
	
	
//	@Configuration
//	@Order(2)
//	public static class FormSecurityConfig extends WebSecurityConfigurerAdapter{
//		@Value("${security.signing-key}")
//		private String signingKey;
//
//		@Value("${security.encoding-strength}")
//		private Integer encodingStrength;
//
//		@Value("${security.security-realm-2}")
//		private String securityRealm;
//
//		@Autowired
//		private UserDetailsService userDetailsService;
//
//		@Bean
//		@Override
//		protected AuthenticationManager authenticationManager() throws Exception {
//			return super.authenticationManager();
//		}
//
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(userDetailsService)
//			        .passwordEncoder(new ShaPasswordEncoder(encodingStrength));
//		}
		
	//	
		
		//
//		  protected void configure(HttpSecurity http) throws Exception {
//		        http
//		            .authorizeRequests().
//		            antMatchers("/login*").permitAll()
//		            .antMatchers("/**")
//		            .hasAnyAuthority("STANDARD_USER","ADMIN_USER")
//		            .and()
//		            .formLogin()
//		           
//		           
//		            .defaultSuccessUrl("/welcome.jsp")
//		            
//		           .and()
//		           .sessionManagement()
//		           .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//		           // formLogin configuration
////		            .and()
////		            .exceptionHandling()
////		            .defaultAuthenticationEntryPointFor(
////		              loginUrlauthenticationEntryPointWithWarning(),
////		              new AntPathRequestMatcher("/user/private/**"))
////		            .defaultAuthenticationEntryPointFor(
////		              loginUrlauthenticationEntryPoint(), 
////		              new AntPathRequestMatcher("/user/general/**"))
//		            ;
//		    }
////	 
////		  @Bean
////		  public AuthenticationEntryPoint loginUrlauthenticationEntryPoint(){
////		      return new LoginUrlAuthenticationEntryPoint("/userLogin");
////		  }
////		           
////		  @Bean
////		  public AuthenticationEntryPoint loginUrlauthenticationEntryPointWithWarning(){
////		      return new LoginUrlAuthenticationEntryPoint("/userLoginWithWarning");
////		  }
////		  
////	    @Bean
////	    public AuthenticationEntryPoint authenticationEntryPoint(){
////	        BasicAuthenticationEntryPoint entryPoint = 
////	          new BasicAuthenticationEntryPoint();
////	        entryPoint.setRealmName("admin realm");
////	        return entryPoint;
////	    }
//		//
//		
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		http
////		        .sessionManagement()
////		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////		        .and()
////		        .httpBasic()
////		        .realmName(securityRealm)
////		        .and()
////		        .csrf()
////		        .disable();
////
////	  }
//
//	
//	
//	
//	}
	
}