package com.Abhi.BlogApp.Secuity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Abhi.BlogApp.entity.User;
import com.Abhi.BlogApp.exception.ResourceNotFoundException;
import com.Abhi.BlogApp.repository.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by username
		
		User user = this.userRepo.findByEmail(username)
		.orElseThrow(()->new ResourceNotFoundException("User","email"+username, 0));
		
		return user;
	}

}
