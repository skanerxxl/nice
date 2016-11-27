package ru.skanerxxl.rambler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skanerxxl.rambler.database.entity.User;

@Service
public class ServiceSecurity implements UserDetailsService {
    @Autowired
    private ServiceUser serviceUser;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = serviceUser.getUser(login);
        UserDetails userDetails = new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getUsername(), user.getPassword(), user.getRole());
        return userDetails;
    }
}
