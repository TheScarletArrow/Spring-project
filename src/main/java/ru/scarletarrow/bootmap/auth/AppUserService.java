package ru.scarletarrow.bootmap.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public AppUserService(@Qualifier("fake") UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.selectAppUserByUserName(s).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
