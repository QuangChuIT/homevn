package com.home.app.security;

import com.home.app.model.Account;
import com.home.app.model.Role;
import com.home.app.service.account.AccountService;
import com.home.app.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountService.findByUserName(s);
        if (account != null) {
            List<GrantedAuthority> grantedAuthorities = getUserAuthority(account.getRoles());
            return buildAccountForAuthentication(account, grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User name not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new ArrayList<>(roles);
    }

    private UserDetails buildAccountForAuthentication(Account account, List<GrantedAuthority> grantedAuthorityList) {
        return new User(account.getUserName(), account.getPassWord(), grantedAuthorityList);
    }

    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
}
