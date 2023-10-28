package com.pooh.notebook.service.impl;

import com.pooh.notebook.Entity.Role;
import com.pooh.notebook.Entity.User;
import com.pooh.notebook.dto.LoginDto;
import com.pooh.notebook.dto.RegisterDto;
import com.pooh.notebook.exception.NoteAPIException;
import com.pooh.notebook.repository.RoleRepository;
import com.pooh.notebook.repository.UserRepository;
import com.pooh.notebook.security.JwtTokenProvider;
import com.pooh.notebook.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service

public class AuthServiceImpl implements AuthService {

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String register(RegisterDto registerDto) {
        //check if username already exists
        if (userRepository.existsByUsername(registerDto.getUsername())) {
        throw new NoteAPIException(HttpStatus.BAD_REQUEST,"Username Already Exists");
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new NoteAPIException(HttpStatus.BAD_REQUEST,"Email Already Exists");
        }
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());

        Set<Role> roleSet = new HashSet<>();
        Role role =roleRepository.findRoleByName("ROLE_USER");
        roleSet.add(role);

        user.setRoles(roleSet);
        userRepository.save(user);

        return "User Added Succesfully";
    }


    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}
