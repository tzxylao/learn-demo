package com.example.oauth2.service.impl;

import com.example.oauth2.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author laoliangliang
 * @date 2019/3/14 17:53
 */
@Service
public class CustomUserDetailService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("bobo");
        userInfo.setPassword("xyz");
        return userInfo;
    }
}
