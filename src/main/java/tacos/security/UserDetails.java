package tacos.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetails implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return null;
	}

}
