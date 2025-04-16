package com.oidcapp.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .anyRequest().authenticated());
        http.oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/oidcappclient"))
                .oauth2Client(Customizer.withDefaults());
        return http.build();
    }

    // @Bean
    // public RestClient restClient(RestClient.Builder builder,
    //     OauthClientRequestInterceptor requestInterceptor) {
    //     //OAuth2ClientHttpRequestInterceptor requestInterceptor = new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);
    //     return builder.requestInterceptor(requestInterceptor).build();
    // }

    @Bean
	public RestClient restClient(RestClient.Builder builder, OAuth2AuthorizedClientManager authorizedClientManager) {
		OAuth2ClientHttpRequestInterceptor requestInterceptor =
			new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);

		return builder.requestInterceptor(requestInterceptor).build();
	}

}
