package ua.tsebulia.AuthorizationAndAuthentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.tsebulia.AuthorizationAndAuthentication.config.UserInfoUserDetails;
import ua.tsebulia.AuthorizationAndAuthentication.entity.UserInfo;
import ua.tsebulia.AuthorizationAndAuthentication.repo.UserInfoRepo;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repo.findByName(username);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException("user not found " + username)
                );
    }
}
