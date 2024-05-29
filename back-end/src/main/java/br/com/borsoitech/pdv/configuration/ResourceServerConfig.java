package br.com.borsoitech.pdv.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${cors.origins}")
	private String corsOrigins;
	
	private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**", "/products/**", "/clientes/**", "/vendas/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html" };
	private static final String[] OPERATOR_OR_ADMIN	 = { "/products/**" };
	private static final String[] ADMIN	 = { "/users/**" };
	
	private Environment environment;
	private JwtTokenStore jwtTokenStore;
	
	public ResourceServerConfig(Environment environment, JwtTokenStore jwtTokenStore) {
		this.environment = environment;
		this.jwtTokenStore = jwtTokenStore;
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(jwtTokenStore);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers(headers -> headers.frameOptions().disable());
		}

        http.authorizeRequests(requests -> requests
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, OPERATOR_OR_ADMIN).permitAll()
                .antMatchers(OPERATOR_OR_ADMIN).hasAnyRole("ADMIN", "OPERATOR")
                .antMatchers(ADMIN).hasRole("ADMIN")
                .anyRequest().authenticated());

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		String[] origins = corsOrigins.split(",");

	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOriginPatterns(Arrays.asList(origins));
	    corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
	    corsConfig.setAllowCredentials(true);
	    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	 
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);
	    return source;
	}

	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
}
