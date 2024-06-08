package br.com.borsoitech.pdv.service;

import br.com.borsoitech.pdv.entity.User;
import br.com.borsoitech.pdv.repository.UserRepository;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Email not found"));
		return user.get();
	}
}
