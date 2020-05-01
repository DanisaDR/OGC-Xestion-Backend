package org.redeoza.xestion.configuration;

import java.util.Arrays;

import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.utils.CustomTokenEnhancer;
import org.redeoza.xestion.utils.UtilConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) {
		try {
			clients.inMemory().withClient("redeoza").secret(passwordEncoder.encode("abcd1234.")).scopes("read", "write")
					.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(36000)
					.refreshTokenValiditySeconds(36000);
		} catch (final Exception e) {
			throw new GenericException(e.getMessage());
		}
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		final TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

		enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).tokenEnhancer(enhancerChain)
				.exceptionTranslator(loggingExceptionTranslator());
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	private TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter jwt = new JwtAccessTokenConverter();
		jwt.setSigningKey(UtilConstant.RSA_PRIVATE);
		jwt.setVerifierKey(UtilConstant.RSA_PUBLIC);
		return jwt;
	}

	@Bean
	public WebResponseExceptionTranslator<OAuth2Exception> loggingExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
			e.getMessage();

			final ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
			final HttpHeaders headers = new HttpHeaders();
			headers.setAll(responseEntity.getHeaders().toSingleValueMap());
			final OAuth2Exception excBody = responseEntity.getBody();
			return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
			}
		};
	}
}
