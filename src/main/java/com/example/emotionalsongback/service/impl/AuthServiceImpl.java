package com.example.emotionalsongback.service.impl;

import com.example.emotionalsongback.dto.LoginDto;
import com.example.emotionalsongback.dto.RegisterDto;
import com.example.emotionalsongback.entity.Playlist;
import com.example.emotionalsongback.entity.Role;
import com.example.emotionalsongback.entity.Utente;
import com.example.emotionalsongback.exception.APIException;
import com.example.emotionalsongback.repository.RoleRepository;
import com.example.emotionalsongback.repository.UtenteRepository;
import com.example.emotionalsongback.security.JwtTokenProvider;
import com.example.emotionalsongback.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UtenteRepository utenteRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public String register(RegisterDto registerDto) {

        if(utenteRepository.existsByUsername(registerDto.getUsername())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Username inserito è già esistente!");
        }

        if(utenteRepository.existsByUsername(registerDto.getEmail())){
            throw new APIException(HttpStatus.BAD_REQUEST, "L'email inserita è già esistente!");
        }

        Utente utente = modelMapper.map(registerDto,Utente.class);
        utente.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role utenteRole = roleRepository.findByName("ROLE_USER");
        roles.add(utenteRole);

        Set<Playlist> playlists = new HashSet<>();

        utente.setRoles(roles);
        utente.setPlaylists(playlists);
        utenteRepository.save(utente);

        return "Utente registrato correttamente";


    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    public Utente getUtenteFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            Utente utente = utenteRepository.findByUsernameOrEmail(user.getUsername(), user.getUsername()).get();
            return utente;
        }
        return null;
    }
}

