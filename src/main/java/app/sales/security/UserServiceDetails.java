package app.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import app.sales.domain.repository.repositoryUsers;
import app.sales.domain.entity.user;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceDetails implements UserDetailsService {
    private final PasswordEncoder passwordEncoder = new Config().passwordEncoder();
    @Autowired
    private repositoryUsers repUsers;

    @Transactional
    public user savePassEncrypted(user us){
        us.setPassword(passwordEncoder.encode(us.getPassword()));
        return repUsers.save(us);
    }

    @Override
    public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{
        user us = repUsers
                    .findByName(userName)
                    .orElseThrow(()-> new RuntimeException("userName not found"));

        String role = null;
        if(us.getIsAdmin() == 1) role = new String("ADMIN");
        return User.builder()
                .username(us.getName())
                .password(us.getPassword())
                .roles(role)
                .build();
    }

    public UserDetails loadUserByEmail(String email)throws UsernameNotFoundException{
        user us = repUsers
                .findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not found"));

        String role = null;
        if(us.getIsAdmin() == 1) role = new String("ADMIN");
        return User.builder()
                .username(us.getEmail())
                .password(us.getPassword())
                .roles(role)
                .build();
    }

    public UserDetails autenticar (user us){
        UserDetails usDet = loadUserByUsername(us.getName());
        //boolean isPassTrue = passwordEncoder.matches(us.getPassword(),usDet.getPassword());
        boolean isPassTrue = us.getPassword().equals(usDet.getPassword());
        if (isPassTrue) return usDet;
        throw new RuntimeException("Pass invalid");
    }
}
