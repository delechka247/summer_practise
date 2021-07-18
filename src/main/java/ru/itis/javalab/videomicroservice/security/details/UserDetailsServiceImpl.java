package ru.itis.javalab.videomicroservice.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.videomicroservice.security.jwt.utils.JwtDecoder;

@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        return new UserDetailsImpl(jwtDecoder.getUserFromJwt(token));
    }
}