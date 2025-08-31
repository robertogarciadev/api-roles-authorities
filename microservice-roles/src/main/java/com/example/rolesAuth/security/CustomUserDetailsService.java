package com.example.rolesAuth.security;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.example.rolesAuth.service.UserService;
import com.example.rolesAuth.util.SecurityUtils;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        CustomUserPrincipal customUserPrincipal = userService.findByMail(mail)
                .map(userDTO -> {
                    //Convert Role to SimpleGrantedAuthority
                    SimpleGrantedAuthority role = SecurityUtils.convertToAuthority(userDTO.getRole());

                    //Convert Permission to SimpleGrantedAuthority
                    Set<SimpleGrantedAuthority> permissionList = userDTO.getRole().getListPermission().stream()
                    .map(permission-> new SimpleGrantedAuthority(permission.getName()))
                    .collect(Collectors.toSet());

                    // It is created to Set<GrantedAuthority> for insert roles and permission and then
                    //pass a propertie to CustomUserPrincipal()
                    Set<GrantedAuthority> authorities = new HashSet<>();
                    authorities.add(role);
                    authorities.addAll(permissionList);


                    return CustomUserPrincipal.builder()
                            .id(userDTO.getId())
                            .mail(userDTO.getMail())
                            .name(userDTO.getName())
                            .lastName(userDTO.getLastName())
                            .authorities(authorities).build();

                }).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return customUserPrincipal;
    }
}
