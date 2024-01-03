package br.devrafaelsoares.storeapirestful.services;

import br.devrafaelsoares.storeapirestful.domain.authentication.dto.AuthenticationRequest;
import br.devrafaelsoares.storeapirestful.domain.authentication.dto.AuthenticationResponse;
import br.devrafaelsoares.storeapirestful.domain.authentication.dto.RegisterResponse;
import br.devrafaelsoares.storeapirestful.domain.authentication.dto.RegisterRequest;
import br.devrafaelsoares.storeapirestful.domain.user.Role;
import br.devrafaelsoares.storeapirestful.domain.user.User;
import br.devrafaelsoares.storeapirestful.exceptions.auth.UserExistsException;
import br.devrafaelsoares.storeapirestful.exceptions.auth.UserNotFoundException;
import br.devrafaelsoares.storeapirestful.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;

@Service
public class AuthenticationService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(
            @NotNull AuthenticationRequest request
    ) {


        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UserNotFoundException("Usuário ou senha informados são inválidos"));

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );

        authenticationManager.authenticate(usernamePassword);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                    .moment(Instant.now().atZone(ZoneId.systemDefault()))
                    .message("Usuário logado com sucesso")
                    .username(user.getUsername())
                    .role(user.getRole())
                    .token(token)
                .build();
    }

    public RegisterResponse register(
            @NotNull RegisterRequest request
    ) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new UserExistsException("Usuário já cadastrado");
        };

        User newUser = User
                .builder()
                    .username(request.username())
                    .password(encryptedPassword)
                    .role(Role.valueOf(request.role()))
                .build();

        userRepository.save(newUser);

        return RegisterResponse
                .builder()
                    .moment(Instant.now().atZone(ZoneId.systemDefault()))
                    .message("Usuário registrado com sucesso")
                    .username(newUser.getUsername())
                    .role(newUser.getRole())
                .build();
    }
}
