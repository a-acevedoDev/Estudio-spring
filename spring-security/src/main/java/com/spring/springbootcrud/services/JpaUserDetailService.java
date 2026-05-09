package com.spring.springbootcrud.services;

import com.spring.springbootcrud.entities.User;
import com.spring.springbootcrud.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByUsername(username);
        if (optionalUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s no se encuentra en el sistema.", username));
        }
        User user = optionalUser.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> {
                    String roleName = role.getName();
                    // Si el rol ya tiene ROLE_, lo dejamos igual, si no, lo agregamos
                    if (!roleName.startsWith("ROLE_")) {
                        roleName = "ROLE_" + roleName;
                    }
                    System.out.println("Cargando rol: " + roleName); // Debug
                    return new SimpleGrantedAuthority(roleName);
                })
                .collect(Collectors.toList());

        System.out.println("Usuario: " + user.getUsername() + " - Roles: " + authorities); // Debug

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}