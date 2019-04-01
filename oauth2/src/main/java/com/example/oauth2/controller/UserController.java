package com.example.oauth2.controller;

import com.example.oauth2.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laoliangliang
 * @date 2019/3/13 10:37
 */
@Controller
public class UserController {
    // 资源API
    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        UserInfo userinfo = (UserInfo) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = userinfo.getUsername() + "@163.com";

        UserInfo userInfo = new UserInfo();
        userInfo.setName(userinfo.getUsername());
        userInfo.setEmail(email);

        return ResponseEntity.ok(userInfo);
    }

}
