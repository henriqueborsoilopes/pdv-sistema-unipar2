package br.com.borsoitech.pdv.configuration.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import br.com.borsoitech.pdv.entity.User;
import br.com.borsoitech.pdv.repository.UserRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	private final UserRepository userRepository;
	
	public JwtTokenEnhancer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Optional<User> user = userRepository.findByEmail(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();
		map.put("userFirstName", user.isPresent() ? user.get().getUsername() : "");
		map.put("userId", user.isPresent() ? user.get().getId() : "");

		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);
		
		return accessToken;
	}
}
