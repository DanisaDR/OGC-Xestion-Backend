package org.redeoza.xestion.utils;

import java.util.HashMap;
import java.util.Map;

import org.redeoza.xestion.dao.ILoginDao;
import org.redeoza.xestion.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	ILoginDao loginDao;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		final Login login = loginDao.findByUsuAlias(authentication.getName());

		final Map<String, Object> additionalInfo = new HashMap<>();

		additionalInfo.put("usuID", login.getUsuID());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

		return accessToken;
	}
}
