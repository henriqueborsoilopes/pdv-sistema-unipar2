package br.com.borsoitech.pdv.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.borsoitech.pdv.entity.User;
import br.com.borsoitech.pdv.repository.UserRepository;
import br.com.borsoitech.pdv.service.exception.ForbiddenException;
import br.com.borsoitech.pdv.service.exception.UnauthorizedException;


@Service
public class AuthService {

	private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	public User authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username).orElseThrow(() -> new UnauthorizedException("Invalid user"));
		}
		catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}

	public void validateSelfOrAdmin(Long userId) {
		User user = this.authenticated();
		if (!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied");
		}
	}
}
