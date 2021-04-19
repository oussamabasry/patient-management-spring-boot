package basry.app.security;

import basry.app.dao.UserRepository;
import basry.app.entities.Role;
import basry.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        List<GrantedAuthority> authorities = new
                ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles())
            authorities.add(new SimpleGrantedAuthority("ROLE_" +
                    role.getRole()));
        UserDetails userDetails =
                User.withUsername(user.getUsername()).password(user.getPassword())
                        .authorities(authorities).build();
        return userDetails;
    }
}

