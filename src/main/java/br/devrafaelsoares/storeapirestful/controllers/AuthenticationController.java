package br.devrafaelsoares.storeapirestful.controllers;

import br.devrafaelsoares.storeapirestful.domain.authentication.dto.AuthenticationRequest;
import br.devrafaelsoares.storeapirestful.domain.authentication.dto.AuthenticationResponse;
import br.devrafaelsoares.storeapirestful.domain.authentication.dto.RegisterRequest;
import br.devrafaelsoares.storeapirestful.domain.authentication.dto.RegisterResponse;
import br.devrafaelsoares.storeapirestful.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody @Valid RegisterRequest request
    )  {
        return ResponseEntity.ok(authenticationService.register(request));
    }

}
