package ru.org.spring.services;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ru.org.spring.model.User;
import ru.org.spring.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserDetailsHelp userServiceHelp;
    @Autowired
    public UserDetailsServiceImpl(UserDetailsHelp userServiceHelp) {
        this.userServiceHelp = userServiceHelp;
    }

    public User findByName(String name) {
        return userServiceHelp.findByName(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", name));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
